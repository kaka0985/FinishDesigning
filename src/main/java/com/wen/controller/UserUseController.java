package com.wen.controller;

import com.wen.pojo.Product;
import com.wen.pojo.ResponseVO;
import com.wen.pojo.User;
import com.wen.service.UserUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @作者：温
 * @时间：2023/3/1 14:53
 */
@RestController
@Configuration
@RequestMapping("/api")
public class UserUseController {
    @Autowired
    private UserUseService userUseService;


    //登录@Configuration
    @RequestMapping("/login")
    public ResponseVO<User> login(User user) {

        User user2 = userUseService.selectByUserName(user.getUserid(), user.getPassword());
        if (user2 != null) {
            return new ResponseVO(user2, "success", 0, "密码正确");
        }
        return new ResponseVO(null, "error", 0, "用户名或者密码错误");
    }

    @RequestMapping("/searchByname")
    public ResponseVO<String> searchByname(String text) {
        List<Product> list = userUseService.searchByName(text);
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询不到");
    }
}
