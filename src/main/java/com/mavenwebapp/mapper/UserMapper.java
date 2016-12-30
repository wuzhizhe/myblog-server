package com.mavenwebapp.mapper;

import com.mavenwebapp.entity.User;
import com.mavenwebapp.entity.UserLoginHis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

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

        @Select("SELECT * FROM user WHERE id = #{id}")
        public User getUserByUserId(@Param("id") String id);

        @Update("UPDATE user SET username = #{username}, " +
                " password = #{password}, " +
                " nickname = #{nickname}, " +
                " status = #{status}, " +
                " name = #{name}, " +
                " sex = #{sex}, " +
                " telphone = #{telphone}, " +
                " createtime = #{createtime}, " +
                " updatetime = #{updatetime}, " +
                " lastlogintime = #{lastlogintime}, " +
                " headimage = #{headimage} " +
                " WHERE id = #{id}")
        public void updateUser(User user);

        @Insert("insert into userloginhis (username, logintime, loginip) values ( #{username}, #{logintime}, #{loginip})")
        public void  insertUserLoginHis(UserLoginHis ulh);

        @Select("SELECT id, username, DATE_FORMAT(logintime, '%Y-%m-%d %T') as logintimestr, loginip " +
                "FROM userloginhis WHERE username = #{username} ORDER BY logintime DESC limit 10")
        public List<UserLoginHis> getUserLoginHis(String username);

}
