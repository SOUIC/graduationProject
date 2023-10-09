package com.scitc.blogend.entity;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.utils.DataUtils;
import lombok.Getter;

@Getter
public class ResponseToken {
    private final Integer status;
    private final String msg;
    private final String time;
    private final String token;

    public ResponseToken(String token) {
        this.status = StatusCodeConstant.LOGIN_SUCCESS.getCode();
        this.msg = StatusCodeConstant.LOGIN_SUCCESS.getMessage();
        this.time = DataUtils.getTime("yyyy-MM-dd HH:mm:ss");
        this.token = token;
    }

    public static ResponseToken token(String token) {
        return new ResponseToken(token);
    }
}
