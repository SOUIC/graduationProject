package com.scitc.blogend.service;

import com.scitc.blogend.entity.Classify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【classify(分类表)】的数据库操作Service
* @createDate 2022-11-10 16:43:16
*/
public interface ClassifyService extends IService<Classify> {
    Boolean newClassify(String classify);
    Boolean updateClassify(Integer classifyId, String newClassify);
    List<Classify> getAllClassify();
    void deleteClassify(Integer classifyId);
}
