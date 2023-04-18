package com.wen.pojo;

import lombok.Data;

/**
 * @作者：温
 * @时间：2023/4/5 18:43
 */
@Data
public class VoucherUsage {
    private String VoucherUsage_id;
    private String VoucherUsage_userID;
    private String VoucherUsage_voucherID;
    private String VoucherUsage_CreateTime;

    public String getVoucherUsage_id() {
        return VoucherUsage_id;
    }

    public void setVoucherUsage_id(String voucherUsage_id) {
        VoucherUsage_id = voucherUsage_id;
    }

    public String getVoucherUsage_userID() {
        return VoucherUsage_userID;
    }

    public void setVoucherUsage_userID(String voucherUsage_userID) {
        VoucherUsage_userID = voucherUsage_userID;
    }

    public String getVoucherUsage_voucherID() {
        return VoucherUsage_voucherID;
    }

    public void setVoucherUsage_voucherID(String voucherUsage_voucherID) {
        VoucherUsage_voucherID = voucherUsage_voucherID;
    }

    public String getVoucherUsage_CreateTime() {
        return VoucherUsage_CreateTime;
    }

    public void setVoucherUsage_CreateTime(String voucherUsage_CreateTime) {
        VoucherUsage_CreateTime = voucherUsage_CreateTime;
    }
}
