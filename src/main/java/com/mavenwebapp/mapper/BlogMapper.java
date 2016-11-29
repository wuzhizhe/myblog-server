package com.mavenwebapp.mapper;

import com.mavenwebapp.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzemu on 2016/11/29.
 */

@Component
public interface BlogMapper {
    @Insert("insert into blog (id, blogname, userid, tags, status) values ( #{id}, #{blogname}, #{userid}, #{tags}, #{status} )")
    public void saveBlog(Blog blog);
}
