package com.wen.pojo;

import lombok.Data;

/**
 * @作者：温
 * @时间：2023/2/21 15:47
 */
@Data
public class ProPackage {
    private int package_id;
    private String package_name;//名字
    private String package_price;//价格
    private String package_programId;//节目ID
    private String package_status;//状态
    private String package_programName;//节目名字
    private String package_cannel;//可看频道
    private String package_time;//持续时间
    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_price() {
        return package_price;
    }

    public void setPackage_price(String package_price) {
        this.package_price = package_price;
    }

    public String getPackage_status() {
        return package_status;
    }

    public void setPackage_status(String package_status) {
        this.package_status = package_status;
    }

    public String getPackage_programId() {
        return package_programId;
    }

    public void setPackage_programId(String package_programId) {
        this.package_programId = package_programId;
    }

    public String getPackage_programName() {
        return package_programName;
    }

    public void setPackage_programName(String package_programName) {
        this.package_programName = package_programName;
    }

    public String getPackage_cannel() {
        return package_cannel;
    }

    public void setPackage_cannel(String package_cannel) {
        this.package_cannel = package_cannel;
    }

    public String getPackage_time() {
        return package_time;
    }

    public void setPackage_time(String package_time) {
        this.package_time = package_time;
    }
}
