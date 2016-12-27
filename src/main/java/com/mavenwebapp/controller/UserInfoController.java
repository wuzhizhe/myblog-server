package com.mavenwebapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenwebapp.entity.Image;
import com.mavenwebapp.entity.User;
import com.mavenwebapp.service.ImageService;
import com.mavenwebapp.service.UserService;
import com.mavenwebapp.utils.RequestUtil;
import com.mavenwebapp.utils.SaveImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/12/23.
 */

@Controller
public class UserInfoController {

    @Autowired
    ImageService imageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/updateheadimage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadHeadImage(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            String userId = request.getParameter("userId");
            String imageUrl = request.getParameter("imageUrl");
            User user = userService.getUserByUserId(userId);
            user.setHeadimage(imageUrl);
            userService.updateUser(user);
            map.put("success", true);
            map.put("data", user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        } finally {
            return map;
        }
    }

    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> changePassword(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            String oldPassword = request.getParameter("oldpassword");
            String username = request.getParameter("username");
            String newPassword = request.getParameter("newpassword");
            User user = userService.getUserUsingUsername(username, oldPassword);
            if (user == null) {
                map.put("success", false);
            } else {
                user.setPassword(newPassword);
                userService.updateUser(user);
                user.setPassword(null);
                map.put("success", true);
                map.put("data", user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    public void saveImage(List<Image> images) {
        try {
            imageService.insertImage(images);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("end one upload!");
        }
    }

}
