package com.mavenwebapp.dao;

import com.mavenwebapp.interfaces.BaseDao;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangzemu on 2016/9/14.
 */

@Component
public class BaseDaoImpl implements BaseDao{

    @Autowired
    private DataSource dataSource;

    public <T> List<T> getAllList(String sql, Class type) {
        QueryRunner run = new QueryRunner(dataSource);
        ResultSetHandler<List<T>> handler = new BeanListHandler(type);
        List<T> result = null;
        try {
            result = run.query(sql, handler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
