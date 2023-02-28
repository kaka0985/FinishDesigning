package com.wen.service;

import com.wen.pojo.Order;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/27 10:32
 */
public interface OrderService {
    List<Order> getOrderList(Order order);


    List<Order> selectByOrderUserID(String order_userId, String order_type);
}
