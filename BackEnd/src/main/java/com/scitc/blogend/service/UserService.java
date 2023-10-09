package com.scitc.blogend.service;

import com.scitc.blogend.entity.DTO.UserInfoDTO;
import com.scitc.blogend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author liuruichao
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2022-11-10 16:43:27
*/
public interface UserService extends IService<User> {

    //验证登陆
    Boolean verifyLogin(String username, String password);

    //获取用户数据
    UserInfoDTO getUserInfo();

    //修改用户信息
    Boolean updateUserInfo(String username, String password, String email, String announcement);

    //修改头像
    Boolean updatePicture(HttpServletRequest request, MultipartFile file);

    Long getArticleCnt();
}
