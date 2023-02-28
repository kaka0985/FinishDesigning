package com.wen.service;


import com.wen.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
public interface UserService {
    List<User> getUserList(User user);

    User selectByUsername(String userid);

    List<User> selectByUsername2(String nick_name, String member);

    Integer updateUser(User user);

    Integer addUser(User user);

    Integer deleteUser(int id);
}
