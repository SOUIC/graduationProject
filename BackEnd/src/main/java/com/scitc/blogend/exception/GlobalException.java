package com.scitc.blogend.exception;

import com.scitc.blogend.entity.ResponseInfo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.http.HttpResponse;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = ApiException.class)
    public ResponseInfo apiExceptionHandler(ApiException e) {
        return ResponseInfo.infoCustom(e.getStatus(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseInfo apiExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseInfo.infoCustom(500, "未知错误 错误信息：" + e.getMessage());
    }
}
