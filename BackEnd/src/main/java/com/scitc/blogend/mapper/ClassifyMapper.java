package com.scitc.blogend.mapper;

import com.scitc.blogend.entity.Classify;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【classify(分类表)】的数据库操作Mapper
* @createDate 2022-11-10 16:43:16
* @Entity com.scitc.blogend.entity.Classify
*/
public interface ClassifyMapper extends BaseMapper<Classify> {
    //查询分类
    List<Classify> getAllClassify();
    //查看分类是否存在
    Integer verifyClassify(String classify);
}




