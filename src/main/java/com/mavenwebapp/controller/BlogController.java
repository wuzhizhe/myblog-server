package com.mavenwebapp.controller;

import com.mavenwebapp.utils.RequestUtil;
import com.mavenwebapp.entity.Blog;
import com.mavenwebapp.entity.BlogDetail;
import com.mavenwebapp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangzemu on 2016/11/29.
 */
@Controller
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveBlog(HttpServletRequest request) {
        Map<String, Object> map = new HashMap();
        Map<String, Object> mapjson = RequestUtil.getRequestBody(request);
        Blog blog = new Blog();
        BlogDetail blogDetail = new BlogDetail();
        String uuid = UUID.randomUUID().toString();
        blog.setId(uuid);
        blog.setBlogname(mapjson.get("blogname").toString());
        blog.setUserid(Integer.parseInt(mapjson.get("userid").toString()));
        blog.setTags(mapjson.get("tags").toString());

        blogDetail.setBlogId(uuid);
        blogDetail.setBlogText(mapjson.get("content").toString());
        try {
            blogService.saveBlog(blog, blogDetail);
            map.put("success", true);
            map.put("blog", blog);
            map.put("blogdetail", blogDetail);
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    @RequestMapping(value = "/getBlog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBlog() {
        return null;
    }

    @RequestMapping(value = "/getBlogList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBlogList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapjson = RequestUtil.getRequestBody(request);
        List<Blog> blogs = blogService.getBlogs(mapjson.get("userid").toString());
        map.put("success", true);
        map.put("data", blogs);
        return map;
    }
}
