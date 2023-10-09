package com.scitc.blogend.entity;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.utils.DataUtils;
import lombok.Getter;

@Getter
public class ResponseData<T> {
    private final Integer status;
    private final String msg;
    private final String time;
    private final T data;
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //静态工厂模式
    public static <T> ResponseData<T> data(T data) {
        return new ResponseData<>(data);
    }

    public ResponseData(T data) {
        this.status = StatusCodeConstant.SUCCESS.getCode();
        this.msg = StatusCodeConstant.SUCCESS.getMessage();
        this.time = DataUtils.getTime(TIME_FORMAT);
        this.data = data;
    }
}
