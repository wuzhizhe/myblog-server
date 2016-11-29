package com.mavenwebapp.controller;

import com.mavenwebapp.entity.Blog;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/11/29.
 */
public class SaveBlogController {
    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveBlog(@RequestBody Blog blog) {
        return null;
    }
}
