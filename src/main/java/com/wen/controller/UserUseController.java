package com.wen.controller;

import com.wen.pojo.Result;
import com.wen.pojo.User;
import com.wen.service.UserUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @作者：温
 * @时间：2023/3/1 14:53
 */
@RestController
@RequestMapping("/use")
//@CrossOrigin
public class UserUseController {
    @Autowired
    private UserUseService userUseService;


    @RequestMapping("/login")
    public Result<User> login(@RequestBody User user){

        User user2=userUseService.selectByUserName(user.getUserid(),user.getPassword());
        if(user2!=null){
            return Result.success(user2);
        }
        return Result.fail(20002,"用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<User> getUserInfo(@RequestParam("token") String token){
        // 根据token获取用户信息，redis;
        User user2  = userUseService.selectByUserName(token,token);
        if(user2!=null){
            return Result.success(user2);
        }
        return Result.fail(20003,"登录信息无效，请重新登录");
    }

}
