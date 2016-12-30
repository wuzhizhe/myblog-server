package com.mavenwebapp.entity;

import java.util.Date;

/**
 * Created by zhangzemu on 2016/12/30.
 */
public class UserLoginHis {

    private int id;
    private String username;
    private Date logintime;
    private String loginip;
    private String logintimestr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLogintime() {
        return logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLogintimestr() {
        return logintimestr;
    }

    public void setLogintimestr(String logintimestr) {
        this.logintimestr = logintimestr;
    }
}
