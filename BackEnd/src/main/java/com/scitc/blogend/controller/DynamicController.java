package com.scitc.blogend.controller;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.ResponseInfo;
import com.scitc.blogend.service.impl.DynamicServiceImpl;
import com.scitc.blogend.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, DELETE, PUT})
public class DynamicController {

    @Autowired
    DynamicServiceImpl dynamicService;

    @PostMapping("/newMoment")
    public ResponseInfo newMomet(HttpServletRequest request, @RequestParam("content") String content) {
        TokenUtils.verify(request.getHeader("token"));
        return dynamicService.newMoment(content) ? ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }
    @PutMapping("/updateMoment")
    public ResponseInfo updateMoment(HttpServletRequest request, @RequestParam("dynamicId") Integer dynamicId, @RequestParam("newContent") String newContent) {
        TokenUtils.verify(request.getHeader("Token"));
        return dynamicService.updateMoment(dynamicId, newContent) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    @DeleteMapping("/delMoment")
    public ResponseInfo delMoment(HttpServletRequest request, @RequestParam("dynamicId") Integer dynamicId) {
        TokenUtils.verify(request.getHeader("Token"));
        return dynamicService.deleteMoment(dynamicId) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    @GetMapping("/getAllMoment")
    public Object getAllMoment(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return dynamicService.getAllMoment(pageNum, pageSize);
    }
}
