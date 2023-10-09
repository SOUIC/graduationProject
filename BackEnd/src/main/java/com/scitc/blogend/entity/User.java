package com.scitc.blogend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像url
     */
    private String headUrl;

    /**
     * 建站时间
     */
    private String constructionTime;

    /**
     * 公告
     */
    private String announcement;

    /**
     * 网站访问量
     */
    private Integer visitorVolume;

    /*
    * 文章量
    * */
    private Integer articleNumber;

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
        User other = (User) that;
        return (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getHeadUrl() == null ? other.getHeadUrl() == null : this.getHeadUrl().equals(other.getHeadUrl()))
            && (this.getConstructionTime() == null ? other.getConstructionTime() == null : this.getConstructionTime().equals(other.getConstructionTime()))
            && (this.getAnnouncement() == null ? other.getAnnouncement() == null : this.getAnnouncement().equals(other.getAnnouncement()))
            && (this.getVisitorVolume() == null ? other.getVisitorVolume() == null : this.getVisitorVolume().equals(other.getVisitorVolume()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getHeadUrl() == null) ? 0 : getHeadUrl().hashCode());
        result = prime * result + ((getConstructionTime() == null) ? 0 : getConstructionTime().hashCode());
        result = prime * result + ((getAnnouncement() == null) ? 0 : getAnnouncement().hashCode());
        result = prime * result + ((getVisitorVolume() == null) ? 0 : getVisitorVolume().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userName=").append(userName);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", headUrl=").append(headUrl);
        sb.append(", constructionTime=").append(constructionTime);
        sb.append(", announcement=").append(announcement);
        sb.append(", visitorVolume=").append(visitorVolume);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}