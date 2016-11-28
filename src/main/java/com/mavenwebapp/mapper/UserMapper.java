package com.mavenwebapp.mapper;

import com.mavenwebapp.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * Created by zhangzemu on 2016/11/3.
 */

@Component
public interface UserMapper {
        @Select("SELECT * FROM user WHERE username = #{username} and password = #{password}")
        public User getUserUsingUsername(@Param("username") String username, @Param("password") String password);

        @Insert("INSERT into user(" +
                "username, password, nickname, status, name, sex, telphone, createtime, updatetime" +
                ") " +
                "values (" +
                "#{username}, #{password}, #{nickname}, #{status}, #{name}, #{sex}, #{telphone}, " +
                "#{createtime}, #{updatetime})")
        public void insertUser(User user);


}
