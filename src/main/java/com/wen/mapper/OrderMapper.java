package com.wen.mapper;

import com.wen.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/27 10:35
 */
@Mapper
public interface OrderMapper {
    List<Order> getOrderList(Order order);

    List<Order> selectByOrderUserID(String order_userId, String order_type);
}
