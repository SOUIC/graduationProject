package com.scitc.blogend.constant;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodeConstant {

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    LOGIN_SUCCESS(600, "登陆成功"),
    LOGIN_ERROR(601, "用户名或密码错误"),
    TOKEN_INVALID(602, "Token过期 请重新登陆"),
    NEW_ARTICLE_ERROR(610, "新增文章失败"),
    UPDATE_ARTICLE_ERROR(611, "更新文章失败"),
    AERTICLES_ERROR(612, "文章内容获取失败"),
    CLASSIFY_ERROR(613, "当前分类已经存在"),
    EMPTY_AERTICLE_ERROR(615, "所删除文章不存在"),
    DELETE_ARTICLE_ERROR(616, "删除文章失败"),
    DELETE_ARTICLES_ERROR(617, "部分文章删除失败"),
    EMPTY_FILE(700, "空文件"),
    FILE_ERROR(701, "文件上传失败"),
    FOLDER_ERROR(702, "文件夹创建失败");

    private final Integer code;
    private final String message;

}
