package com.wen.mapper;

import com.wen.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getUserList(User user);

    User selectByUsername(String userid);

    List<User> selectByUsername2(String nick_name,String member);

    Integer updateUser(User user);

    Integer addUser(User user);

    Integer deleteUser(int id);
}
