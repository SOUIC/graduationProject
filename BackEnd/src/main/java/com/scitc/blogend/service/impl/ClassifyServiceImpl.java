package com.scitc.blogend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.Articles;
import com.scitc.blogend.entity.Classify;
import com.scitc.blogend.exception.ApiException;
import com.scitc.blogend.service.ArticlesService;
import com.scitc.blogend.service.ClassifyService;
import com.scitc.blogend.mapper.ClassifyMapper;
import com.scitc.blogend.service.LogService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author liuruichao
* @description 针对表【classify(分类表)】的数据库操作Service实现
* @createDate 2022-11-10 16:43:15
*/
@Service
@Log4j2
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify>
    implements ClassifyService{

    @Autowired
    LogServiceImpl logService;

    @Autowired
    ArticlesServiceImpl articlesService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean newClassify(String classify) {
        if(baseMapper.verifyClassify(classify) > 0) {
            throw new ApiException(StatusCodeConstant.CLASSIFY_ERROR);
        }

        Classify newClassify = new Classify();
        newClassify.setClassify(classify);

        if(baseMapper.insert(newClassify) > 0) {
            logService.insert("新增分类" + classify + "成功");
            log.info("新增分类{}成功", classify);
            return true;
        } else {
            logService.insert("新增分类" + classify + "失败");
            log.error("新增分类{}失败", classify);
            return false;
        }
     }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateClassify(Integer classifyId, String newClassify) {
        Classify classify = new Classify();
        classify.setClassifyId(classifyId);
        classify.setClassify(newClassify);
        if(baseMapper.updateById(classify) > 0) {
            logService.insert("更新分类名称成功");
            log.info("更新分类名称成功");
            return true;
        } else {
            logService.insert("更新分类名称失败");
            log.error("更新分类失败");
            return false;
        }

    }

    @Override
    public List<Classify> getAllClassify() {
        return  baseMapper.getAllClassify();
    }

    @Override
    public void deleteClassify(Integer classifyId) {
        String classifyName = baseMapper.selectById(classifyId).getClassify();

    }
}




