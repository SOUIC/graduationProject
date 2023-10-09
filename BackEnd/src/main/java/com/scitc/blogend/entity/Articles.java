package com.scitc.blogend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 文章表
 * @TableName articles
 */
@TableName(value ="articles")
@Data
public class Articles implements Serializable {
    /**
     * 文章id
     */
    @TableId(type = IdType.AUTO)
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

    /**
     * 文章文件路径
     */
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Articles other = (Articles) that;
        return (this.getArticlesId() == null ? other.getArticlesId() == null : this.getArticlesId().equals(other.getArticlesId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getClassification() == null ? other.getClassification() == null : this.getClassification().equals(other.getClassification()))
            && (this.getLastModifiedDate() == null ? other.getLastModifiedDate() == null : this.getLastModifiedDate().equals(other.getLastModifiedDate()))
            && (this.getAbstracts() == null ? other.getAbstracts() == null : this.getAbstracts().equals(other.getAbstracts()))
            && (this.getRecycleBinState() == null ? other.getRecycleBinState() == null : this.getRecycleBinState().equals(other.getRecycleBinState()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticlesId() == null) ? 0 : getArticlesId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getClassification() == null) ? 0 : getClassification().hashCode());
        result = prime * result + ((getLastModifiedDate() == null) ? 0 : getLastModifiedDate().hashCode());
        result = prime * result + ((getAbstracts() == null) ? 0 : getAbstracts().hashCode());
        result = prime * result + ((getRecycleBinState() == null) ? 0 : getRecycleBinState().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articlesId=").append(articlesId);
        sb.append(", title=").append(title);
        sb.append(", tag=").append(tag);
        sb.append(", classification=").append(classification);
        sb.append(", lastModifiedDate=").append(lastModifiedDate);
        sb.append(", abstracts=").append(abstracts);
        sb.append(", recycleBinState=").append(recycleBinState);
        sb.append(", url=").append(url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}