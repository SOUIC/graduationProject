package com.scitc.blogend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scitc.blogend.constant.StatusCodeConstant;
import com.scitc.blogend.entity.DTO.UserInfoDTO;
import com.scitc.blogend.entity.User;
import com.scitc.blogend.exception.ApiException;
import com.scitc.blogend.service.LogService;
import com.scitc.blogend.service.UserService;
import com.scitc.blogend.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
* @author liuruichao
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-11-10 16:43:27
*/
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    LogServiceImpl logService;


    @Override
    public Boolean verifyLogin(String username, String password) {
        logService.insert("用户登陆");
        log.info("用户登陆{}", username);
        return baseMapper.getPassword(username).equals(password);
    }

    @Override
    public UserInfoDTO getUserInfo() {
        User user = baseMapper.getUserInfo();
        return new UserInfoDTO(user.getUserName(),
                user.getEmail(),
                user.getHeadUrl(),
                user.getConstructionTime(),
                user.getAnnouncement(),
                user.getVisitorVolume(),
                baseMapper.getArticlesCnt());
    }

    @Override
    public Boolean updateUserInfo(String username, String password, String email, String announcement) {
        if(baseMapper.updateUserInfo(username, password, email, announcement) > 0) {
            logService.insert("用户信息更新成功");
            log.info("用户信息更新成功");
            return true;
        } else {
            logService.insert("用户信息更新失败");
            log.info("用户信息更新失败");
            return false;
        }
    }

    public void addVisitorVolume() {
        Integer num = baseMapper.getUserInfo().getVisitorVolume();
        num += 1;
        baseMapper.updateVisitorVolume(num);
    }

    @Override
    public Boolean updatePicture(HttpServletRequest request, MultipartFile multipartFile) {
        File dir = new File("/Users/liuruichao/Program/JAVA/blog_resources/avatar");
        if(!dir.exists()) {
            if (dir.mkdirs()) {
                log.info("文件夹创建成功 文件名{}", dir.getName());
            } else {
                log.error("文件夹创建失败");
                throw new ApiException(StatusCodeConstant.FOLDER_ERROR);
            }
        }

        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

        File file = new File(dir, newFileName);
        String url;

        try {
            multipartFile.transferTo(file);
            url = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + "/images/" + newFileName;
        } catch (Exception e) {
            throw new ApiException(StatusCodeConstant.FILE_ERROR);
        }

        if(baseMapper.updatePicture(url) > 0) {
            logService.insert("图片更新成功");
            return true;
        } else {
            logService.insert("图片更新失败");
            return false;
        }
    }

    @Override
    public Long getArticleCnt() {
        return baseMapper.getArticlesCnt();
    }
}




