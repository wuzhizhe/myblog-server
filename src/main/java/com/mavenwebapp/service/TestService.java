package com.mavenwebapp.service;

import com.mavenwebapp.dao.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangzemu on 2016/9/29.
 */

@Component
public class TestService {

    @Autowired
    BaseDaoImpl baseDao;

    public <T> List<T> getAllTests(Class cls) throws Exception {
        String sql = "select * from test";
        List<T> result = baseDao.getAllList(sql, cls);
        return result;
    }
}
