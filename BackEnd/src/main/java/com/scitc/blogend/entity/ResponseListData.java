package com.scitc.blogend.entity;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.utils.DataUtils;
import lombok.Getter;

@Getter
public class ResponseListData<T> {
    private final Integer status;
    private final String msg;
    private final String time;
    private final Integer pages;
    private final Integer pageNum;
    private final T data;
    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static  <T> ResponseListData<T> data (T data, Integer pages, Integer pageNum) {
        return new ResponseListData<>(data, pages, pageNum);
    }

    public ResponseListData(T data, Integer pages, Integer pageNum) {
        this.status = StatusCodeConstant.SUCCESS.getCode();
        this.msg = StatusCodeConstant.SUCCESS.getMessage();
        this.time = DataUtils.getTime(TIME_FORMAT);
        this.data = data;
        this.pages = pages;
        this.pageNum = pageNum;
    }
}
