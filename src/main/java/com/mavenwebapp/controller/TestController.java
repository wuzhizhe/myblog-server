package com.mavenwebapp.controller;

import com.mavenwebapp.dao.BaseDaoImpl;
import com.mavenwebapp.entity.Test;
import com.mavenwebapp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/9/13.
 */
@Controller

public class TestController {

    @Autowired
    TestService dataService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> test1() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", "falselll");
        return map;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        return "redirect:http://127.0.0.1:7777/index.html";
    }

    @RequestMapping(value = "/getTests", method = RequestMethod.GET)
    @ResponseBody
    public List<Test> getAllTests() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Test> tests = null;
        try {
            tests = dataService.getAllTests(Test.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("result", tests);
        return tests;
    }

}
