package com.scitc.blogend.controller;

import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.ResponseInfo;
import com.scitc.blogend.entity.ResponseToken;
import com.scitc.blogend.service.impl.UserServiceImpl;
import com.scitc.blogend.utils.TokenUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@Log4j2
@CrossOrigin(origins = "*", allowCredentials = "false", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.GET, DELETE, PUT})
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public Object login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        return userService.verifyLogin(userName, password) ? ResponseToken.token(TokenUtils.setToken()) : ResponseInfo.info(StatusCodeConstant.LOGIN_ERROR);
    }
}
