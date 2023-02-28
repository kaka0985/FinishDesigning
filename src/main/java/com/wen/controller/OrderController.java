package com.wen.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.pojo.AjaxResult;
import com.wen.pojo.Order;
import com.wen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/23 15:46
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/table")
    public String toTable(){
        return "OrderTable";
    }
    //查询订单列表
    @RequestMapping("/getOrderList")
    @ResponseBody
    public  Object getOrderList(Integer page, Integer limit, Order order, Model model){
        PageHelper.startPage(page,limit);
        List<Order> sList = orderService.getOrderList(order);
        PageInfo<Order> PageInfo = new PageInfo<>(sList);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",sList);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
    //订单界面模糊查询
    @RequestMapping("/getOrderListByOrderUserId")
    @ResponseBody
    public  Object getOrderListByOrderName(Integer page, Integer limit, Order order, Model model){
        PageHelper.startPage(page,limit);
        //用户界面模糊查询
        List<Order> list=orderService.selectByOrderUserID(order.getOrder_userId(),order.getOrder_type());
        PageInfo<Order> PageInfo = new PageInfo<>(list);
        HashMap<String,Object> resultmap=new HashMap<>();
        model.addAttribute("list",list);
        resultmap.put("code",0);
        resultmap.put("msg","出错啦");
        resultmap.put("count",PageInfo.getTotal());
        resultmap.put("data",PageInfo.getList());
        return resultmap;
    }
}
