package com.scitc.blogend.mapper;

import com.scitc.blogend.entity.Articles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scitc.blogend.entity.DTO.ArticleListDTO;
import com.scitc.blogend.entity.DTO.ArticlesDTO;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【articles(文章表)】的数据库操作Mapper
* @createDate 2022-11-10 16:11:51
* @Entity com.scitc.blogend.entity.Articles
*/
public interface ArticlesMapper extends BaseMapper<Articles> {
    //获取所有文章（无内容）
    List<ArticleListDTO> getAllArticles();
    //将文章放进回收站
    Integer updateArticleRecycleBinState(Integer articleid, Integer state);
    //获取回收站文章
    List<ArticleListDTO> getArticlesInTrash();
    //获取最新的文章
    List<ArticleListDTO> getNewestArticles(Integer number);
    //更新文章分类
    Integer updateArticleClassify(String newClassify, String oldClassify);

    //按分类获取文章
    List<ArticleListDTO> getArticlesByClassfiy(String classify);
    List<Articles> getArticlesByClassfiyFullInfo(String classify);

}




