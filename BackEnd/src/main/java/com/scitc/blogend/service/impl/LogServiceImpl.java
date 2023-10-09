package com.scitc.blogend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scitc.blogend.entity.Log;
import com.scitc.blogend.entity.ResponseData;
import com.scitc.blogend.entity.ResponseListData;
import com.scitc.blogend.service.LogService;
import com.scitc.blogend.mapper.LogMapper;
import com.scitc.blogend.utils.DataUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【log(日志表)】的数据库操作Service实现
* @createDate 2022-11-10 16:43:24
*/
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log>
    implements LogService{

    @Override
    public void insert(String operation) {
        baseMapper.insert(new Log(DataUtils.getTime("yyyy-MM-dd HH:mm:ss"), operation));
    }

    @Override
    public ResponseListData<List<Log>> getLog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Log> list = baseMapper.getAllLogs();
        PageInfo<Log> page = PageInfo.of(list);
        return ResponseListData.data(list, page.getPages(), page.getPageNum());
    }
}




