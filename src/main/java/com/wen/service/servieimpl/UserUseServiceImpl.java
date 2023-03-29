package com.wen.service.servieimpl;

import com.wen.mapper.UserUseMapper;
import com.wen.pojo.Order;
import com.wen.pojo.Product;
import com.wen.pojo.ProductAndUser;
import com.wen.pojo.User;
import com.wen.service.UserUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/3/1 14:55
 */
@Service
public class UserUseServiceImpl implements UserUseService {

    @Autowired
    private UserUseMapper userUseMapper;

    @Override
    public User selectByUserName(String userid, String password) {
        return userUseMapper.selectByUserName(userid,password);
    }

    @Override
    public List<Product> searchByName(String text) {
        return userUseMapper.searchByName(text);
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        return userUseMapper.getOrderByUserId(userId);
    }

    @Override
    public List<ProductAndUser> getProductByProductName(String product_name) {
        return userUseMapper.getProductByProductName(product_name);
    }

    @Override
    public List<User> getProductByUserName(String user_id) {
        return userUseMapper.getProductByUserName(user_id);
    }

    @Override
    public Integer updateUserMember(String user_id) {
        return userUseMapper.updateUserMember(user_id);
    }

    @Override
    public Integer BuyMonth(String reStr,String User_id) {
        return userUseMapper.BuyMonth(reStr,User_id);
    }

    @Override
    public Integer BuyMonthInitial(String format, String reStr, String user_id) {
        return userUseMapper.BuyMonthInitial(format,reStr,user_id);
    }

    @Override
    public Product getProductByProductNameAnother(String product_name) {
        return userUseMapper.getProductByProductNameAnother(product_name);
    }

    @Override
    public Integer addUserOrder(Order order) {
        return userUseMapper.addUserOrder(order);
    }

    @Override
    public List<Product> getProductByCannel(String cannel) {
        return userUseMapper.getProductByCannel(cannel);
    }
}
