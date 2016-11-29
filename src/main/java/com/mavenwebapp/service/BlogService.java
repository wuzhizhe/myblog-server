package com.mavenwebapp.service;

import com.mavenwebapp.entity.Blog;
import com.mavenwebapp.mapper.BlogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
