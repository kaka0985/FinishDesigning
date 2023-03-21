package com.wen.pojo;

import lombok.Data;

/**
 * @作者：温
 * @时间：2023/2/27 10:27
 */
@Data
public class Order {
    private String order_id;//订单ID
    private String order_userId;//订单的用户ID
    private String order_productId;//订单购买的产品ID
    private String order_commodity;//订单购买的套餐或者单个商品名
    private String order_price;//订单价格
    private String order_createTime;//订单创建日期
    private String order_dueTime;//订单截止日期
    private String order_type;//购买类型1，单个购买2，套餐购买
    private String order_cannel;//产品渠道
    private String order_img;//产品图片

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_userId() {
        return order_userId;
    }

    public void setOrder_userId(String order_userId) {
        this.order_userId = order_userId;
    }

    public String getOrder_productId() {
        return order_productId;
    }

    public void setOrder_productId(String order_productId) {
        this.order_productId = order_productId;
    }

    public String getOrder_commodity() {
        return order_commodity;
    }

    public void setOrder_commodity(String order_commodity) {
        this.order_commodity = order_commodity;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder_createTime() {
        return order_createTime;
    }

    public void setOrder_createTime(String order_createTime) {
        this.order_createTime = order_createTime;
    }

    public String getOrder_dueTime() {
        return order_dueTime;
    }

    public void setOrder_dueTime(String order_dueTime) {
        this.order_dueTime = order_dueTime;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getOrder_cannel() {
        return order_cannel;
    }

    public void setOrder_cannel(String order_cannel) {
        this.order_cannel = order_cannel;
    }

    public String getOrder_img() {
        return order_img;
    }

    public void setOrder_img(String order_img) {
        this.order_img = order_img;
    }
}
