package com.mavenwebapp.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangzemu on 2016/9/29.
 */
public class TestMapper implements RowMapper<Test> {
    @Override
    public Test mapRow(ResultSet resultSet, int i) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getInt("id"));
        test.setUsername(resultSet.getString("username"));
        test.setPassword(resultSet.getString("password"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = df.format(resultSet.getTimestamp("createtime"));
        test.setCreatetime(date);
        return test;
    }
}
