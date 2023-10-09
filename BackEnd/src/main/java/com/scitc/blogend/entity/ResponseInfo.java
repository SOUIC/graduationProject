package com.scitc.blogend.entity;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.utils.DataUtils;
import lombok.Data;

@Data
public class ResponseInfo {
    private final Integer status;
    private final String msg;
    private final String time;

    public static ResponseInfo info(StatusCodeConstant statusCodeConstant) {
        return new ResponseInfo(statusCodeConstant);
    }

    public static ResponseInfo infoCustom(Integer status, String msg) {
        return new ResponseInfo(status, msg);
    }

    public ResponseInfo (StatusCodeConstant statusCodeConstant) {
        this.status = statusCodeConstant.getCode();
        this.msg = statusCodeConstant.getMessage();
        this.time = DataUtils.getTime("yyyy-MMM-dd HH:mm:ss");
    }

    public ResponseInfo(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.time = DataUtils.getTime("yyyy-MMM-dd HH:mm:ss");
    }
}
