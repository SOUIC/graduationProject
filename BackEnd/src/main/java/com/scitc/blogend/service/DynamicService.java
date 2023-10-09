package com.scitc.blogend.service;

import com.scitc.blogend.entity.Dynamic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scitc.blogend.entity.ResponseListData;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【dynamic(动态表)】的数据库操作Service
* @createDate 2022-11-10 16:43:20
*/
public interface DynamicService extends IService<Dynamic> {
    Boolean newMoment(String content);
    Boolean updateMoment(Integer dynamicId, String newContent);
    Boolean deleteMoment(Integer dynamicId);
    ResponseListData<List<Dynamic>> getAllMoment(Integer pageNum, Integer pageSize);
}
