package com.mavenwebapp.entity;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by zhangzemu on 2016/10/25.
 */
public class RequestParams {
    private String return_message;
    private String status;
    private String return_data;
    private String token;
    private String request_params;

    public String getReturn_message() {
        return return_message;
    }

    public void setReturn_message(String return_message) {
        this.return_message = return_message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturn_data() {
        return return_data;
    }

    public void setReturn_data(String return_data) {
        this.return_data = return_data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequest_params() {
        return request_params;
    }

    public void setRequest_params(String request_params) {
        this.request_params = request_params;
    }
}
