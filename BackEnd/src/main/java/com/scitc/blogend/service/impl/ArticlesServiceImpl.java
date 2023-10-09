package com.scitc.blogend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.Articles;
import com.scitc.blogend.entity.DTO.ArticleListDTO;
import com.scitc.blogend.entity.DTO.ArticlesDTO;
import com.scitc.blogend.entity.ResponseListData;
import com.scitc.blogend.exception.ApiException;
import com.scitc.blogend.service.ArticlesService;
import com.scitc.blogend.mapper.ArticlesMapper;
import com.scitc.blogend.service.LogService;
import com.scitc.blogend.utils.DataUtils;
import lombok.extern.log4j.Log4j2;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
* @author liuruichao
* @description 针对表【articles(文章表)】的数据库操作Service实现
* @createDate 2022-11-10 16:11:51
*/
@Service
@Log4j2
public class ArticlesServiceImpl extends ServiceImpl<ArticlesMapper, Articles>
    implements ArticlesService{

    @Autowired
    LogServiceImpl logService;

    @Autowired
    UserServiceImpl userService;

    @Override
    public void newArticle(String title, String tag, String classification, String abstracts, String content) {
        log.info("新增文章名称{}", title);

        File dir = new File("/Users/liuruichao/Program/JAVA/blog_resources/articles");
        if(!dir.exists()) {
            if (dir.mkdirs()) {
                log.info("文件夹创建成功");
            } else {
                log.error("文件夹创建失败");
                throw new ApiException(StatusCodeConstant.FOLDER_ERROR);
            }
        }

        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + ".md";
        File file = new File(dir, fileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
            writer.close();
        } catch (IOException e) {

            if (file.delete()) {
                log.info("文章 {} 删除成功(文件)" ,title);
            } else {
                log.error("文章 {} 删除失败(文件)", title);
            }
            logService.insert("文章" + title + "创建失败");
            throw new ApiException(StatusCodeConstant.NEW_ARTICLE_ERROR);
        }

        String url = "/Users/liuruichao/Program/JAVA/blog_resources/articles/" + fileName;
        Articles articles = new Articles();
        articles.setTitle(title);
        articles.setTag(tag);
        articles.setAbstracts(abstracts);
        articles.setClassification(classification);
        articles.setLastModifiedDate(DataUtils.getTime("yyyy-MM-dd HH:mm:ss"));
        articles.setRecycleBinState(0);
        articles.setUrl(url);
        baseMapper.insert(articles);
        logService.insert("创建文章" + title + "成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteArticle(Integer articleId) {
        Articles articles = baseMapper.selectById(articleId);
        if(articles == null) {
            throw new ApiException(StatusCodeConstant.EMPTY_AERTICLE_ERROR);
        }

        String path = articles.getUrl();
        String title = articles.getTitle();
        File file = new File(path);

        if (baseMapper.deleteById(articleId) > 0) {
            if (file.delete()) {
                log.info("文件删除成功(文章)");
                logService.insert("删除文章：" + title);
            } else {
                log.error("文件删除失败(文章)");
                logService.insert("删除文章：" + title + " 失败");
                throw new ApiException(StatusCodeConstant.DELETE_ARTICLES_ERROR);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticle(Integer articleId, String newTitle, String newTag, String newClassification, String newAbstracts, String newContent) {
        Articles articles = new Articles();
        articles.setArticlesId(articleId);
        articles.setTag(newTag);
        articles.setClassification(newClassification);
        articles.setAbstracts(newAbstracts);
        articles.setTitle(newTitle);
        articles.setLastModifiedDate(DataUtils.getTime("yyyy-MM-dd HH:mm:ss"));
        if(baseMapper.updateById(articles) > 0) {
            File file = new File(baseMapper.selectById(articleId).getUrl());
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(newContent);
                bw.close();
            } catch (IOException e) {
                logService.insert("文章更新失败");
                log.error("文章更新失败" + e.getMessage());
                throw new ApiException(StatusCodeConstant.UPDATE_ARTICLE_ERROR);
            }
            logService.insert("文章更新成功");
            log.info("文章更新成功");
        } else {
            logService.insert("文章更新失败");
            log.error("文章更新失败");
            throw new ApiException(StatusCodeConstant.UPDATE_ARTICLE_ERROR);
        }
    }

    @Override
    public ResponseListData<List<ArticleListDTO>> getAllArticles(Integer pageNum, Integer pageSize) {
        userService.addVisitorVolume();
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListDTO> list = baseMapper.getAllArticles();
        PageInfo<ArticleListDTO> page = PageInfo.of(list);
        return ResponseListData.data(list, page.getPages(), page.getPageNum());
    }

    @Override
    public ArticlesDTO getOneArticle(Integer articleId) {
        Articles articles = baseMapper.selectById(articleId);
        String path = articles.getUrl();
        String content;

        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            String temp;
            while((temp = br.readLine()) != null) {
                sb.append(temp).append('\n');
            }

            content = sb.toString();
            br.close();

        } catch (IOException e) {
            log.error("文章获取失败{}",  e.getMessage());
            throw new ApiException(StatusCodeConstant.AERTICLES_ERROR);
        }

        Parser parser = Parser.builder().extensions(tableExtension).build();
        Node document = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(tableExtension).build();
        String str = renderer.render(document);

        return new ArticlesDTO(
                articles.getArticlesId(),
                articles.getTitle(),
                articles.getTag(),
                articles.getClassification(),
                articles.getLastModifiedDate(),
                str
        );
    }

    @Override
    public ArticlesDTO getOneArticleOrigin(Integer articleId) {
        Articles articles = baseMapper.selectById(articleId);
        String path = articles.getUrl();
        String content;
        // 创建表格容器
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                sb.append(temp + "\n");
            }
            content = sb.toString();
            bufferedReader.close();
        } catch (IOException e) {
            log.error("文章获取失败{}",  e.getMessage());
            throw new ApiException(StatusCodeConstant.AERTICLES_ERROR);
        }
        return new ArticlesDTO(
                articles.getArticlesId(),
                articles.getTitle(),
                articles.getTag(),
                articles.getClassification(),
                articles.getLastModifiedDate(),
                content
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean putArticleInTrash(Integer articleId) {
        Articles articles = baseMapper.selectById(articleId);
        if (baseMapper.updateArticleRecycleBinState(articleId, 1) > 0) {
            log.info("文章 {} 放入回收站成功", articles.getTitle());
            logService.insert("将 "+ articles.getTitle() + " 文章放入回收站");
            return true;
        } else {
            log.error("文章 {} 放入回收站失败", articles.getTitle());
            logService.insert("将 "+ articles.getTitle() + " 文章放入回收站失败");
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean recoverArticle(Integer articleId) {
        Articles articles = baseMapper.selectById(articleId);
        if (baseMapper.updateArticleRecycleBinState(articleId, 0) > 0) {
            log.info("恢复 {} 文章成功", articles.getTitle());
            logService.insert("将 "+ articles.getTitle() + " 文章恢复");
            return true;
        } else {
            log.error("恢复 {} 文章失败", articles.getTitle());
            logService.insert("文章 "+ articles.getTitle() + " 恢复失败");
            return false;
        }
    }

    @Override
    public ResponseListData<List<ArticleListDTO>> getArticlesInTrash(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListDTO> list = baseMapper.getArticlesInTrash();
        PageInfo<ArticleListDTO> page = PageInfo.of(list);
        return ResponseListData.data(list, page.getPages(), page.getPageNum());
    }

    @Override
    public List<ArticleListDTO> getNewArticle(Integer number) {
        return baseMapper.getNewestArticles(number);
    }

    @Override
    public ResponseListData<List<ArticleListDTO>> getArticlesByClassify(String classify, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleListDTO> list = baseMapper.getArticlesByClassfiy(classify);
        PageInfo<ArticleListDTO> page = PageInfo.of(list);
        return ResponseListData.data(list, page.getPages(), page.getPageNum());
    }

    @Override
    public String uploadPicture(HttpServletRequest request, MultipartFile multipartFile) {
        File dir = new File("/Users/liuruichao/Program/JAVA/blog_resources/blog_img");
        if(!dir.exists()) {
            if (dir.mkdirs()) {
                log.info("文件夹创建成功 文件名{}", dir.getName());
            } else {
                log.error("文件夹创建失败");
                throw new ApiException(StatusCodeConstant.FOLDER_ERROR);
            }
        }

        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

        File file = new File(dir, newFileName);
        String url;

        try {
            multipartFile.transferTo(file);
            url = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + "/blog_img/" + newFileName;
        } catch (Exception e) {
            throw new ApiException(StatusCodeConstant.FILE_ERROR);
        }

        return url;
    }

    public void updateArticleClassify(String newClassify, String oldClassify) {
        baseMapper.updateArticleClassify(newClassify, oldClassify);
    }

    public List<Articles> getArticlesByClassify(String classify) {
        return baseMapper.getArticlesByClassfiyFullInfo(classify);
    }

    public void deleteArticles(Integer articleId) {
        baseMapper.deleteById(articleId);
    }
}




