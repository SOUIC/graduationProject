package com.scitc.blogend.service;

import com.scitc.blogend.entity.Log;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scitc.blogend.entity.ResponseListData;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【log(日志表)】的数据库操作Service
* @createDate 2022-11-10 16:43:24
*/
public interface LogService extends IService<Log> {

    void insert(String operation);

    ResponseListData<List<Log>> getLog(Integer pageNum, Integer pageSize);
}
