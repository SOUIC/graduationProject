package com.scitc.blogend.service;

import com.scitc.blogend.entity.Articles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scitc.blogend.entity.DTO.ArticleListDTO;
import com.scitc.blogend.entity.DTO.ArticlesDTO;
import com.scitc.blogend.entity.ResponseListData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author liuruichao
* @description 针对表【articles(文章表)】的数据库操作Service
* @createDate 2022-11-10 16:11:51
*/
public interface ArticlesService extends IService<Articles> {
    void newArticle(String title, String tag, String classification, String abstracts, String content);
    void deleteArticle(Integer articleId);
    void updateArticle(Integer articleId, String newTitle, String newTag, String newClassification, String newAbstracts, String newContent);
    ResponseListData<List<ArticleListDTO>> getAllArticles(Integer pageNum, Integer pageSize);
    ArticlesDTO getOneArticle(Integer articleId);
    ArticlesDTO getOneArticleOrigin(Integer articleId);
    Boolean putArticleInTrash(Integer articleId);
    Boolean recoverArticle(Integer articleId);
    ResponseListData<List<ArticleListDTO>> getArticlesInTrash(Integer pageNum, Integer pageSize);
    List<ArticleListDTO> getNewArticle(Integer number);
    ResponseListData<List<ArticleListDTO>> getArticlesByClassify(String classify, Integer pageNum, Integer pageSize);
    String uploadPicture(HttpServletRequest request, MultipartFile multipartFile);
}
