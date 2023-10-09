package com.scitc.blogend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.scitc.blogend.entity.Dynamic;
import com.scitc.blogend.entity.ResponseListData;
import com.scitc.blogend.service.DynamicService;
import com.scitc.blogend.mapper.DynamicMapper;
import com.scitc.blogend.service.LogService;
import com.scitc.blogend.utils.DataUtils;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【dynamic(动态表)】的数据库操作Service实现
* @createDate 2022-11-10 16:43:20
*/
@Service
@Log4j2
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic>
    implements DynamicService{

    @Autowired
    LogServiceImpl logService;

    String time = DataUtils.getTime("yyyy-MM-dd HH:mm:ss");


    @Override
    public Boolean newMoment(String content) {
        Dynamic dynamic = new Dynamic();
        dynamic.setTime(time);
        dynamic.setContent(content);
        if(baseMapper.insert(dynamic) > 0) {
            logService.insert("新增动态成功");
            log.info("新增动态成功");
            return true;
        } else {
            logService.insert("新增动态失败");
            log.error("新增动态失败");
            return false;
        }
    }

    @Override
    public Boolean updateMoment(Integer dynamicId, String newContent) {
        Dynamic dynamic = new Dynamic();
        dynamic.setTime(time);
        dynamic.setDynamicId(dynamicId);
        dynamic.setContent(newContent);
        baseMapper.updateById(dynamic);
        if(baseMapper.updateById(dynamic) > 0) {
            logService.insert("动态更新成功");
            log.info("动态更新成功");
            return true;
        } else {
            logService.insert("动态更新失败");
            log.error("动态更新失败");
            return false;
        }
    }

    @Override
    public Boolean deleteMoment(Integer dynamicId) {
        if(baseMapper.deleteById(dynamicId) > 0) {
            logService.insert("动态删除成功");
            log.info("动态删除成功");
            return true;
        } else {
            logService.insert("动态删除失败");
            log.error("动态删除失败");
            return false;
        }
    }

    @Override
    public ResponseListData<List<Dynamic>> getAllMoment(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dynamic> list = baseMapper.getAllMoments();
        PageInfo<Dynamic> page = PageInfo.of(list);
        return ResponseListData.data(list, page.getPages(), page.getPageNum());
    }
}




