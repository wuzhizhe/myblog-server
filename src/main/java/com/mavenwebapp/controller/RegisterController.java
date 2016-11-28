package com.mavenwebapp.controller;

import com.mavenwebapp.entity.User;
import com.mavenwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/11/3.
 */

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        Boolean success = true;
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        } finally {
            map.put("success", success);
        }
        return map;
    }
}
