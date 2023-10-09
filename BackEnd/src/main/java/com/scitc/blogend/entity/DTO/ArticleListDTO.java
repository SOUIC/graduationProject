package com.scitc.blogend.entity.DTO;

import lombok.Data;

@Data
public class ArticleListDTO {
    private Integer articlesId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 标签
     */
    private String tag;

    /**
     * 分类
     */
    private String classification;

    /**
     * 最后修改日期
     */
    private String lastModifiedDate;

    /**
     * 摘要
     */
    private String abstracts;

    /**
     * 0不再回收站
     1在回收站
     */
    private Integer recycleBinState;
}
