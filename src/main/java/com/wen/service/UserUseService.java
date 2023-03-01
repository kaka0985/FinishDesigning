package com.wen.service;

import com.wen.pojo.User;

/**
 * @作者：温
 * @时间：2023/3/1 14:54
 */
public interface UserUseService {
    User selectByUserName(String userid, String password);
}
