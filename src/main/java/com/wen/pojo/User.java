package com.wen.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @作者：温
 * @时间：2023/2/10 20:13
 */
@Data
public class User {
    private int id;
    private String userid;//唯一性
    private String password;
    private String nick_name;
    private String age;
    private String sex;
    private String address;
    private String Grade;//等级
    private String member;//会员 0不是 1是
    private String member_start;//开始时间
    private String member_end;//结束时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMember_start() {
        return member_start;
    }

    public void setMember_start(String member_start) {
        this.member_start = member_start;
    }

    public String getMember_end() {
        return member_end;
    }

    public void setMember_end(String member_end) {
        this.member_end = member_end;
    }
}
