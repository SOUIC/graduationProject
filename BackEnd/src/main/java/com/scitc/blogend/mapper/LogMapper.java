package com.scitc.blogend.mapper;

import com.scitc.blogend.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【log(日志表)】的数据库操作Mapper
* @createDate 2022-11-10 16:43:24
* @Entity com.scitc.blogend.entity.Log
*/
public interface LogMapper extends BaseMapper<Log> {
    List<Log> getAllLogs();
}




