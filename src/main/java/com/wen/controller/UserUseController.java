package com.wen.controller;

import com.wen.pojo.*;
import com.wen.service.UserUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    @RequestMapping("/getOrderByUserId")
    public ResponseVO<String> getOrderByUserId(String userId) {
        List<Order> list = userUseService.getOrderByUserId(userId);
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "没有购买产品");
    }

    //获取产品信息
    @RequestMapping("/getProductByProductName")
    public ResponseVO<String> getProductByProductName(String Product_name, String User_id) {

        List<ProductAndUser> list = userUseService.getProductByProductName(Product_name);
        List<User> list2 = userUseService.getProductByUserName(User_id);
        list.get(0).setUserList(list2);
        return new ResponseVO(list, "success", 0, "查询成功");
    }

    @RequestMapping("/getUser")
    public ResponseVO<String> getUser(String User_id) {
        List<User> list = userUseService.getProductByUserName(User_id);
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);

        //进行时间上的比较，如果会员已经过期就修改传入前端的值并且修改后台的会员等级
        if (list.get(0).getMember().equals("1")) {
            Integer i=list.get(0).getMember_end().compareTo(format);
            if(i!=1){
                list.get(0).setMember("0");
                list.get(0).setMember_start(null);
                list.get(0).setMember_end(null);
                userUseService.updateUserMember(User_id);
            }
        }
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "没有该用户");
    }
}
