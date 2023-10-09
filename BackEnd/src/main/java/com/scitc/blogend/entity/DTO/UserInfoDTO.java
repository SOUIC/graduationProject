package com.scitc.blogend.entity.DTO;

import lombok.Data;

@Data
public class UserInfoDTO {
    /**
     * 用户名
     */
    private String userName;

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
    private Long articleNumber;

    public UserInfoDTO(String userName, String email, String headUrl, String constructionTime, String announcement, Integer visitorVolume, Long articleNumber) {
        this.userName = userName;
        this.email = email;
        this.headUrl = headUrl;
        this.constructionTime = constructionTime;
        this.announcement = announcement;
        this.visitorVolume = visitorVolume;
        this.articleNumber = articleNumber;
    }
}
