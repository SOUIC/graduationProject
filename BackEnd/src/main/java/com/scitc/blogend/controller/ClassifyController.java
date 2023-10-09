package com.scitc.blogend.controller;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.Classify;
import com.scitc.blogend.entity.ResponseData;
import com.scitc.blogend.entity.ResponseInfo;
import com.scitc.blogend.service.impl.ClassifyServiceImpl;
import com.scitc.blogend.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, DELETE, PUT})
public class ClassifyController {
    @Autowired
    ClassifyServiceImpl classifyServiceImpl;

    @PostMapping("/newClassify")
    public ResponseInfo newClassify(HttpServletRequest request, @RequestParam("classify") String classify) {
        TokenUtils.verify(request.getHeader("Token"));
        return classifyServiceImpl.newClassify(classify) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    @PutMapping("/updateClassify")
    public ResponseInfo updateClassify(HttpServletRequest request, @RequestParam("classifyId") Integer classifyId, @RequestParam("newClassify") String newClassify) {
        TokenUtils.verify(request.getHeader("Token"));
        return classifyServiceImpl.updateClassify(classifyId, newClassify) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    @DeleteMapping("/delClassify")
    public ResponseInfo deleteClassify(HttpServletRequest request, @RequestParam("classifyId") Integer classifyId) {
        TokenUtils.verify(request.getHeader("Token"));
        classifyServiceImpl.deleteClassify(classifyId);
        return ResponseInfo.info(StatusCodeConstant.SUCCESS);
    }

    @GetMapping("/getClassify")
    public ResponseData<List<Classify>> getClassify() {
        return ResponseData.data(classifyServiceImpl.getAllClassify());
    }
}
