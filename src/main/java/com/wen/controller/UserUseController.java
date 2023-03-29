package com.wen.controller;

import com.wen.pojo.*;
import com.wen.service.UserUseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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

    //根据产品名进行模糊查询
    @RequestMapping("/searchByname")
    public ResponseVO<String> searchByname(String text) {
        List<Product> list = userUseService.searchByName(text);
        if (list != null && !list.isEmpty()) {
            return new ResponseVO(list, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询不到");
    }

    //获取订单信息
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

    //电影页面返回产品信息和订单信息
    @RequestMapping("/getUserOrderAndProduct")
    public ResponseVO<String> getUserOrderAndProduct(String Product_name,String User_id){
        List<ProductAndUser> productList = userUseService.getProductByProductName(Product_name);
        List<User> userList = userUseService.getProductByUserName(User_id);
        List<Order> orderList = userUseService.getOrderByUserId(User_id);
        if(orderList!=null){
            for(int i=0;i<orderList.size();i++){
                if(orderList.get(i).getOrder_productId().equals(String.valueOf(productList.get(0).getProduct_id()))){
                    productList.get(0).setOrderStatus(1);
                }
            }
        }

        productList.get(0).setUserList(userList);
        return new ResponseVO(productList, "success", 0, "查询成功");
    }


    //获取用户信息 并且进行校验 会员日期过期的就会进行修改
    @RequestMapping("/getUser")
    public ResponseVO<String> getUser(String User_id) {
        List<User> list = userUseService.getProductByUserName(User_id);
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);

        //进行时间上的比较，如果会员已经过期就修改传入前端的值并且修改后台的会员等级
        if (list.get(0).getMember().equals("1")) {
            Integer i = list.get(0).getMember_end().compareTo(format);
            if (i != 1) {
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

    //续费会员
    @RequestMapping("/BuyMember")
    public ResponseVO<String> BuyMember(String month, String User_id) {
        List<User> list = userUseService.getProductByUserName(User_id);
        Integer i = null;
        if (list.get(0).getMember().equals("1")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = null;
            try {
                dt = sdf.parse(list.get(0).getMember_end());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, Integer.parseInt(month));
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            i = userUseService.BuyMonth(reStr, User_id);
        } else {
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            //当前时间加一个月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = null;
            try {
                dt = sdf.parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);
            rightNow.add(Calendar.MONTH, Integer.parseInt(month));
            Date dt1 = rightNow.getTime();
            String reStr = sdf.format(dt1);
            i=userUseService.BuyMonthInitial(format,reStr,User_id);
        }
        if (i==1){
            return new ResponseVO(null, "success", 0, "开通成功");

        }
        return new ResponseVO(null, "error", 0, "开通失败");
    }

    //购买电影 生成订单
    @RequestMapping("/BuyFilm")
    public ResponseVO<String> BuyFilm(String Price,String User_id,String Product_name){
        Product product = userUseService.getProductByProductNameAnother(Product_name);
        Order order=new Order();
        order.setOrder_userId(User_id);
        order.setOrder_productId(String.valueOf(product.getProduct_id()));
        order.setOrder_commodity(product.getProduct_name());
        order.setOrder_price(Price);

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        order.setOrder_createTime(format);
        order.setOrder_dueTime("9999-99-99");

        order.setOrder_type("1");
        order.setOrder_cannel("2");
        order.setOrder_img(product.getProduct_img());
        Integer i=userUseService.addUserOrder(order);
        if(i==1){
            return new ResponseVO(null, "success", 0, "购买成功");
        }
        return new ResponseVO(null, "error", 0, "购买失败");
    }

    //获取各个频道的产品信息
    @RequestMapping("/getProductByCannel")
    public  ResponseVO<String> getProductByCannel(String cannel){
        List<Product> productList=userUseService.getProductByCannel(cannel);
        if(productList!=null){
            return new ResponseVO(productList, "success", 0, "查询成功");
        }
        return new ResponseVO(null, "error", 0, "查询失败");
    }
}
