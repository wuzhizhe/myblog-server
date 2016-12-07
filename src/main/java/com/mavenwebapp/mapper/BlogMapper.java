package com.mavenwebapp.mapper;

import com.mavenwebapp.entity.Blog;
import com.mavenwebapp.entity.BlogDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangzemu on 2016/11/29.
 */

@Component
public interface BlogMapper {
    @Insert("insert into blog (id, blogname, userid, tags, status) values ( #{id}, #{blogname}, #{userid}, #{tags}, #{status} )")
    public void saveBlog(Blog blog);

    @Insert("insert into blogdetail (blogId, blogText) values (#{blogId}, #{blogText})")
    public void saveBlogdetail(BlogDetail blogDetail);

    @Select("select * from blog where blogid = #{blogId}")
    public Blog getBlogById(String blogId);

    @Select("select * from blog where userid = #{userid}")
    public List<Blog> getBlogsByUserId(String userid);

    @Select("select * from blogdetail where blogid in #{blogids}")
    public List<BlogDetail> getBlogDetails(String blogids);

    @Select("select * from blogdetail where blogid = #{blogid}")
    public BlogDetail getBlogDetailById(String blogId);

}
