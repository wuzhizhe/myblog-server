package com.mavenwebapp.service;

import com.mavenwebapp.dao.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangzemu on 2016/10/18.
 */

@Component
public class LoginService {
    @Autowired
    BaseDaoImpl baseDao;

    public <T> List<T> getUSer(Class cls,
                               String username,
                               String password) throws Exception {
        String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
        List<T> result = baseDao.getAllList(sql, cls);
        return result;
    }
}
