package com.scitc.blogend.controller;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.DTO.ArticleListDTO;
import com.scitc.blogend.entity.ResponseData;
import com.scitc.blogend.entity.ResponseInfo;
import com.scitc.blogend.entity.ResponseListData;
import com.scitc.blogend.service.impl.ArticlesServiceImpl;
import com.scitc.blogend.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, DELETE, PUT})
public class ArticleController {
    @Autowired
    ArticlesServiceImpl articlesServiceImpl;

    /**
     * 新增文章接口
     **/
    @PostMapping("/newArticle")
    public ResponseInfo newArticle(
            @RequestParam("title") String title,
            @RequestParam("tag") String tag,
            @RequestParam("classification") String classification,
            @RequestParam("abstracts") String abstracts,
            @RequestBody String content
    ) {
        articlesServiceImpl.newArticle(title, tag, classification, abstracts, content);
        return ResponseInfo.info(StatusCodeConstant.SUCCESS);
    }

    /**
     * 删除文章接口
     **/
    @DeleteMapping("/delArticle")
    public ResponseInfo deleteArticle(HttpServletRequest request, @RequestParam("articleId") Integer articleId) {
        TokenUtils.verify(request.getHeader("Token"));
        articlesServiceImpl.deleteArticle(articleId);
        return ResponseInfo.info(StatusCodeConstant.SUCCESS);
    }

    /**
     * 更新文章接口
     **/
    @PutMapping("/updateArticle")
    public ResponseInfo updateArticle(
            HttpServletRequest request,
            @RequestParam("articleId") Integer articleId,
            @RequestParam("title") String title,
            @RequestParam("tag") String tag,
            @RequestParam("classification") String classification,
            @RequestParam("abstracts") String abstracts,
            @RequestBody String content
    ) {
        TokenUtils.verify(request.getHeader("Token"));
        articlesServiceImpl.updateArticle(articleId, title, tag, classification, abstracts, content);
        return ResponseInfo.info(StatusCodeConstant.SUCCESS);
    }

    /**
     * 获取所有文章接口
     **/
    @GetMapping("/getAllArticles")
    public ResponseListData<List<ArticleListDTO>> getAllArticles(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return articlesServiceImpl.getAllArticles(pageNum, pageSize);
    }

    /**
     * 获取单个文章内容接口
     **/
    @GetMapping("/getOneArticles")
    public Object getOneArticles(@RequestParam("articleId") Integer articleId) {
        return articlesServiceImpl.getOneArticle(articleId);
    }

    /**
     * 获取单个文章内容接口
     **/
    @GetMapping("/getOneArticlesPrototype")
    public Object getOneArticlesPrototype(@RequestParam("articleId") Integer articleId) {
        return articlesServiceImpl.getOneArticleOrigin(articleId);
    }

    /**
     * 将文章放入回收站接口
     **/
    @PutMapping("/putArticleInTrash")
    public ResponseInfo putArticleInTrash(HttpServletRequest request, @RequestParam("articleId") Integer articleId) {
        TokenUtils.verify(request.getHeader("Token"));
        return articlesServiceImpl.putArticleInTrash(articleId) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    /**
     * 恢复文章
     **/
    @PutMapping("/recoverArticle")
    public ResponseInfo recoverArticle(HttpServletRequest request, @RequestParam("articleId") Integer articleId) {
        TokenUtils.verify(request.getHeader("Token"));
        return articlesServiceImpl.recoverArticle(articleId) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    /**
     * 获取在回收站文章接口
     **/
    @GetMapping("/getArticleInTrash")
    public ResponseListData<List<ArticleListDTO>> getArticleInTrash(HttpServletRequest request, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        TokenUtils.verify(request.getHeader("Token"));
        return articlesServiceImpl.getArticlesInTrash(pageNum, pageSize);
    }

    /**
     * 获取最新文章接口
     **/
    @GetMapping("/getNewArticle")
    public ResponseData<List<ArticleListDTO>> getNewArticle(@RequestParam("number") Integer number) {
        return ResponseData.data(articlesServiceImpl.getNewArticle(number));
    }

    /**
     * 按分类获取文章
     **/
    @GetMapping("/getArticlesByClassify")
    public ResponseListData<List<ArticleListDTO>> getArticlesByClassify(@RequestParam("classify") String classify, @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return articlesServiceImpl.getArticlesByClassify(classify, pageNum, pageSize);
    }

    /**
     * 上传图片
     **/
    @PostMapping("/uploadPictures")
    public Object uploadPictures(HttpServletRequest request, @RequestPart("img") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return ResponseInfo.info(StatusCodeConstant.EMPTY_FILE);
        }
        return ResponseData.data(articlesServiceImpl.uploadPicture(request, multipartFile));
    }
}
