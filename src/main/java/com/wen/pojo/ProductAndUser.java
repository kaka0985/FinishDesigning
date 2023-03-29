package com.wen.pojo;

import lombok.Data;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/2/20 15:04
 */

@Data
public class ProductAndUser {
    private int product_id;
    private String product_name;
    private String product_cannel;//1直播，2电视剧，3电影，4少儿，5综艺，6新闻，7体育，8纪实，9生活
    private Float product_price;//价格
    private String product_package;//套餐信息
    private String product_status;//状态 0未上线 1已上线
    private String product_img;//产品图片
    private String product_desc;//产品描述
    private String product_actor;//主演
    private String product_episode;//剧集
    private String product_style;//类型

    private List<User> userList;
    private int  OrderStatus;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_cannel() {
        return product_cannel;
    }

    public void setProduct_cannel(String product_cannel) {
        this.product_cannel = product_cannel;
    }

    public Float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Float product_price) {
        this.product_price = product_price;
    }

    public String getProduct_package() {
        return product_package;
    }

    public void setProduct_package(String product_package) {
        this.product_package = product_package;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_actor() {
        return product_actor;
    }

    public void setProduct_actor(String product_actor) {
        this.product_actor = product_actor;
    }

    public String getProduct_episode() {
        return product_episode;
    }

    public void setProduct_episode(String product_episode) {
        this.product_episode = product_episode;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public int getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        OrderStatus = orderStatus;
    }
}
