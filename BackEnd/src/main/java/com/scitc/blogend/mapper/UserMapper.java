package com.scitc.blogend.mapper;

import com.scitc.blogend.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author liuruichao
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2022-11-10 16:43:27
* @Entity com.scitc.blogend.entity.User
*/
public interface UserMapper extends BaseMapper<User> {
    //获取密码
    String getPassword(String username);
    //获取用户信息
    User getUserInfo();
    //更新用户信息
    Integer updateUserInfo(String username, String password, String email, String announcement);
    //新增阅读量
    void updateVisitorVolume(Integer num);
    //查询文章数量
    Long getArticlesCnt();
    //更新头像
    Integer updatePicture(String url);
}




