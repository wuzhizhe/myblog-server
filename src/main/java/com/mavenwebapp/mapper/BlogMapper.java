package com.mavenwebapp.mapper;

import com.mavenwebapp.entity.Blog;
import com.mavenwebapp.entity.BlogComment;
import com.mavenwebapp.entity.BlogDetail;
import com.mavenwebapp.entity.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("select * from blog where id = #{blogId}")
    public Blog getBlogById(String blogId);

    @Select("select * from blog where userid = #{userid}")
    public List<Blog> getBlogsByUserId(String userid);

    @Select("select * from blogdetail where blogid in #{blogids}")
    public List<BlogDetail> getBlogDetails(String blogids);

    @Select("select * from blogdetail where blogid = #{blogid}")
    public BlogDetail getBlogDetailById(String blogId);

    @Update("update blog set blogname = #{blogname}, tags = #{tags} where id = #{id}")
    public void updateBlog(Blog blog);

    @Update("update blogdetail set blogtext = #{blogText} where blogId = #{blogId}")
    public void updateBlogDetail(BlogDetail bd);

    @Insert("insert into blogcomment (id, blogId, cmId, content, status, crtTime, userId, email) values (" +
            " #{id}, #{blogId}, #{cmId}, #{content}, #{status}, #{crtTime}, #{userId}, #{email})")
    public void saveBlogComment(BlogComment bc);

    @Insert("insert into message ( userId, blogId, content, status) values (" +
            " #{userId}, #{blogId}, #{content}, #{status})")
    public void saveMessage(Message msg);

    @Select("select * from blogcomment where blogid = #{blogId}")
    public List<BlogComment> getCommentsByBlogId(String blogId);

    @Update("update blogcomment set content = #{content} where id = #{id}")
    public void updateComment(BlogComment bc);

}
