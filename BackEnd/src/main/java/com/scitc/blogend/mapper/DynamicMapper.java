package com.scitc.blogend.mapper;

import com.scitc.blogend.entity.Dynamic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【dynamic(动态表)】的数据库操作Mapper
* @createDate 2022-11-10 16:43:20
* @Entity com.scitc.blogend.entity.Dynamic
*/
public interface DynamicMapper extends BaseMapper<Dynamic> {
    //获取所有动态
    List<Dynamic> getAllMoments();

}




