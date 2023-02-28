package com.wen.service.servieimpl;

import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import com.wen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUserList(User user) {
        return userMapper.getUserList(user);
    }

    @Override
    public User selectByUsername(String userid) {
        return userMapper.selectByUsername(userid);
    }

    @Override
    public List<User> selectByUsername2(String nick_name,String member) {
        return userMapper.selectByUsername2(nick_name,member);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public Integer deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

}
