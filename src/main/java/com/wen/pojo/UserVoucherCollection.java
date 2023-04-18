package com.wen.pojo;

import lombok.Data;

import java.util.List;

/**
 * @作者：温
 * @时间：2023/4/4 17:41
 */
@Data
public class UserVoucherCollection {
    private String voucher_id;
    private String voucher_name;
    private String voucher_count;//数量
    private String voucher_scene;//应用场景，1.无门槛 2.会员专享 3.电影 4.电视剧
    private String start_time;
    private String end_time;
    private String voucher_mon;//优惠金额
    private String voucher_desc;//优惠描述
    private String userCollections;//用户是否领取

    public String getUserCollections() {
        return userCollections;
    }

    public void setUserCollections(String userCollections) {
        this.userCollections = userCollections;
    }

    public String getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(String voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_name() {
        return voucher_name;
    }

    public void setVoucher_name(String voucher_name) {
        this.voucher_name = voucher_name;
    }

    public String getVoucher_count() {
        return voucher_count;
    }

    public void setVoucher_count(String voucher_count) {
        this.voucher_count = voucher_count;
    }

    public String getVoucher_scene() {
        return voucher_scene;
    }

    public void setVoucher_scene(String voucher_scene) {
        this.voucher_scene = voucher_scene;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getVoucher_mon() {
        return voucher_mon;
    }

    public void setVoucher_mon(String voucher_mon) {
        this.voucher_mon = voucher_mon;
    }

    public String getVoucher_desc() {
        return voucher_desc;
    }

    public void setVoucher_desc(String voucher_desc) {
        this.voucher_desc = voucher_desc;
    }
}
