package com.mavenwebapp.controller;

import com.mavenwebapp.entity.RequestParams;
import com.mavenwebapp.entity.User;
import com.mavenwebapp.service.LoginService;
import com.mavenwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/10/14.
 */

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        String username = user.getUsername();
        String password = user.getPassword();
        User rUser = null;
        List<User> users = null;
        try {
//            users = loginService.getUSer(User.class, username, password);
            rUser = userService.getUserUsingUsername(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rUser != null) {
                userInfo.put("username", rUser.getUsername());
                userInfo.put("sex", rUser.getSex());
                userInfo.put("id", rUser.getId());
                userInfo.put("telphone", rUser.getTelphone());
                userInfo.put("nickname", rUser.getNickname());
                rUser.setPassword(null);
                map.put("data", rUser);
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        }
        return map;
    }
}
