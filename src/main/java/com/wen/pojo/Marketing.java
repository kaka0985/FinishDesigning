package com.wen.pojo;

import lombok.Data;

/**
 * @作者：温
 * @时间：2023/2/23 10:34
 */
@Data
public class Marketing {

    private String marketing_id;
    private String marketing_name;
    private String marketing_people;//适用人群 0全部 1会员 2普通
    private String marketing_dis;//折扣
    private String marketing_status;//状态
    private String start_time;
    private String end_time;

    public String getMarketing_id() {
        return marketing_id;
    }

    public void setMarketing_id(String marketing_id) {
        this.marketing_id = marketing_id;
    }

    public String getMarketing_name() {
        return marketing_name;
    }

    public void setMarketing_name(String marketing_name) {
        this.marketing_name = marketing_name;
    }

    public String getMarketing_people() {
        return marketing_people;
    }

    public void setMarketing_people(String marketing_people) {
        this.marketing_people = marketing_people;
    }

    public String getMarketing_dis() {
        return marketing_dis;
    }

    public void setMarketing_dis(String marketing_dis) {
        this.marketing_dis = marketing_dis;
    }

    public String getMarketing_status() {
        return marketing_status;
    }

    public void setMarketing_status(String marketing_status) {
        this.marketing_status = marketing_status;
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
}
