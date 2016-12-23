package com.mavenwebapp.service;

import com.mavenwebapp.entity.User;
import com.mavenwebapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangzemu on 2016/11/3.
 */

@Service("userService")
@Transactional
public class UserService  {

    @Autowired
    private UserMapper userMapper;

    public User getUserUsingUsername(String username, String password) {
        return userMapper.getUserUsingUsername(username, password);
    }

    public void saveUser(User user){
        userMapper.insertUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public User getUserByUserId(String id) {
        return userMapper.getUserByUserId(id);
    }
}
