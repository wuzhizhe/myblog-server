package com.mavenwebapp.service;

import com.mavenwebapp.entity.Blog;
import com.mavenwebapp.entity.BlogDetail;
import com.mavenwebapp.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/11/29.
 */

@Service("blogService")
@Transactional
public class BlogService {

    @Autowired
    private BlogMapper blogMapper;

    public void saveBlog(Blog blog) {
        blogMapper.saveBlog(blog);
    }

    public void saveBlog(Blog blog, BlogDetail blogDetail) {
        blogMapper.saveBlog(blog);
        blogMapper.saveBlogdetail(blogDetail);
    }

    public Blog getBlog(String blogId) {
        return blogMapper.getBlogById(blogId);
    }

    public BlogDetail getBlogDetailByBlogId(String blogId) {
        return blogMapper.getBlogDetailById(blogId);
    }

    public List<Blog> getBlogs(String userId) {
        List<Blog> blogs = blogMapper.getBlogsByUserId(userId);
        return blogs;
    }

    public void updateBlogAndDetail(Blog blog, BlogDetail bd) {
        blogMapper.updateBlog(blog);
        blogMapper.updateBlogDetail(bd);;
    }
}
