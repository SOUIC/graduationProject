package com.scitc.blogend.controller;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.DTO.UserInfoDTO;
import com.scitc.blogend.entity.ResponseData;
import com.scitc.blogend.entity.ResponseInfo;
import com.scitc.blogend.service.impl.LogServiceImpl;
import com.scitc.blogend.service.impl.UserServiceImpl;
import com.scitc.blogend.utils.TokenUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, DELETE, PUT})
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    LogServiceImpl logService;

    //获取用户信息
    @GetMapping("/getUserInfo")
    public ResponseData<UserInfoDTO> getUserInfo() { return ResponseData.data(userService.getUserInfo());}

    //更新用户信息
    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/updateUserInfo")
    public ResponseInfo updateUserInfo(
            HttpServletRequest request,
            @RequestParam("userName") String userName,
            @RequestParam("password") String newPassword,
            @RequestParam("email") String newEmail,
            @RequestParam("announcement") String announcement) {
        TokenUtils.verify(request.getHeader("Token"));
        return userService.updateUserInfo(userName, newPassword, newEmail, announcement) ?
                ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }

    //获取日志
    @GetMapping("/getLog")
    public Object getLog(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return logService.getLog(pageNum, pageSize);
    }

    //修改头像
    @PutMapping("/updatePicture")
    public ResponseInfo updatePicture(HttpServletRequest request, @RequestPart("img") MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            return ResponseInfo.info(StatusCodeConstant.EMPTY_FILE);
        }
        return userService.updatePicture(request, multipartFile) ? ResponseInfo.info(StatusCodeConstant.SUCCESS) : ResponseInfo.info(StatusCodeConstant.FAIL);
    }
}
