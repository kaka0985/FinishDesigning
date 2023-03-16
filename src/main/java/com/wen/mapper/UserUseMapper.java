package com.wen.mapper;

import com.wen.pojo.Product;
import com.wen.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/3/1 14:55
 */
@Mapper
public interface  UserUseMapper {
    User selectByUserName(String userid,String password);

    List<Product> searchByName(String text);
}
