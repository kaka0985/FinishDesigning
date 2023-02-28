package com.wen.service.servieimpl;

import com.wen.mapper.OrderMapper;
import com.wen.pojo.Order;
import com.wen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/27 10:34
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> getOrderList(Order order) {
        return orderMapper.getOrderList(order);
    }

    @Override
    public List<Order> selectByOrderUserID(String order_userId, String order_type) {
        return orderMapper.selectByOrderUserID(order_userId,order_type);
    }
}
