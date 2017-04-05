package com.mavenwebapp.controller;

import com.mavenwebapp.entity.BlogComment;
import com.mavenwebapp.entity.Message;
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
        Map<String, Object> tempmap = new HashMap();
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
            tempmap.put("blog", blog);
            tempmap.put("blogdetail", blogDetail);
            map.put("data", tempmap);
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        } finally {
            return map;
        }
    }

    @RequestMapping(value = "/getBlog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBlog(HttpServletRequest request) {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> tempMap = new HashMap<>();
        Map<String, Object> jsonMap = RequestUtil.getRequestBody(request);
        String blogId = jsonMap.get("blogid").toString();
        Blog blog = blogService.getBlog(blogId);
        BlogDetail bd = blogService.getBlogDetailByBlogId(blogId);
        tempMap.put("blog", blog);
        tempMap.put("blogDetail", bd);
        returnMap.put("success", true);
        returnMap.put("data", tempMap);
        return returnMap;
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

    @RequestMapping(value = "/updateBlog", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateBlog(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapjson = RequestUtil.getRequestBody(request);
        Blog blog = new Blog();
        BlogDetail bd = new BlogDetail();
        String blogId = mapjson.get("blogid").toString();
        blog.setId(blogId);
        blog.setTags(mapjson.get("tags").toString());
        blog.setBlogname(mapjson.get("blogname").toString());
        bd.setBlogId(blogId);
        bd.setBlogText(mapjson.get("content").toString());
        blogService.updateBlogAndDetail(blog, bd);
        map.put("success", true);
        map.put("data", blogId);
        return map;
    }

    @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveComment(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> jsonMap = RequestUtil.getRequestBody(request);
            String blogId = jsonMap.get("blogId").toString();
            String content = jsonMap.get("content").toString();
            String cmid = jsonMap.get("cmid").toString();
            Integer userId = Integer.parseInt(jsonMap.get("userId").toString());
            BlogComment bc = new BlogComment();
            bc.setBlogId(blogId);
            bc.setContent(content);
            bc.setUserId(userId);
            bc.setId(cmid);
            Message msg = new Message();
            msg.setUserId(userId);
            msg.setBlogId(blogId);
            msg.setContent("新的评论回复");

            blogService.saveComment(bc, msg);

            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        } finally {
            return map;
        }
    }

    @RequestMapping(value = "/getComments", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getComments(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            Map<String, Object> jsonMap = RequestUtil.getRequestBody(request);
            String blogId = jsonMap.get("blogId").toString();
            List<BlogComment> bcs = blogService.getCommentsByBlogId(blogId);
            map.put("success", true);
            map.put("data", bcs);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        } finally {
            return map;
        }
    };

    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateComment(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Map<String, Object> jsonMap = RequestUtil.getRequestBody(request);
            String blogId = jsonMap.get("blogId").toString();
            String content = jsonMap.get("content").toString();
            String cmid = jsonMap.get("cmid").toString();
            Integer userId = Integer.parseInt(jsonMap.get("userId").toString());
            List<BlogComment> bcs = blogService.getCommentsByBlogId(blogId);
            BlogComment bc = bcs.get(0);
//            bc.setBlogId(blogId);
            bc.setContent(content);
//            bc.setUserId(userId);
//            bc.setId(cmid);
            blogService.updateComment(bc, null);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        } finally {
            return map;
        }
    }

}
