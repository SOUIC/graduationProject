package com.scitc.blogend.exception;

import com.scitc.blogend.constant.StatusCodeConstant;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    //状态码
    private final Integer status;

    //错误信息
    private final String msg;

    public ApiException(StatusCodeConstant statusCodeConstant) {
        super(statusCodeConstant.getMessage());
        this.status = statusCodeConstant.getCode();
        this.msg = statusCodeConstant.getMessage();
    }
}
