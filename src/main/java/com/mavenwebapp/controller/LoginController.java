package com.mavenwebapp.controller;

import com.mavenwebapp.entity.RequestParams;
import com.mavenwebapp.entity.User;
import com.mavenwebapp.entity.UserLoginHis;
import com.mavenwebapp.service.LoginService;
import com.mavenwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
    public Map<String, Object> login(@RequestBody User user, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> userInfo = new HashMap<>();
        String username = user.getUsername();
        String password = user.getPassword();
        User rUser = null;
        List<User> users = null;
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        try {
            rUser = userService.getUserUsingUsername(username, password);
            Date loginDate = new Date();
            if (rUser != null) {
                userInfo.put("username", rUser.getUsername());
                userInfo.put("sex", rUser.getSex());
                userInfo.put("id", rUser.getId());
                userInfo.put("telphone", rUser.getTelphone());
                userInfo.put("nickname", rUser.getNickname());
                //更新user
                rUser.setLastlogintime(loginDate);
                UserLoginHis ulh = new UserLoginHis();
                ulh.setUsername(rUser.getUsername());
                ulh.setLogintime(loginDate);
                ulh.setLoginip(ipAddress);
                userService.saveUserLoginHis(ulh);
                userService.updateUser(rUser);
                rUser.setPassword(null);
                map.put("data", rUser);
                map.put("success", true);
            } else {
                map.put("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    @RequestMapping(value = "/loginhis", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginhis(HttpServletRequest request) {
        String username = request.getParameter("username");
        Map<String, Object> map = new HashMap<>();
        List<UserLoginHis> ulhs = null;
        try {
            ulhs = userService.getUserLoginHis(username);
            map.put("success", true);
            map.put("data", ulhs);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        } finally {
            return map;
        }
    }

}
