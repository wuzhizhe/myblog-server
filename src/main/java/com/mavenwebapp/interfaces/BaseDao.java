package com.mavenwebapp.interfaces;

import com.mavenwebapp.entity.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * Created by zhangzemu on 2016/9/14.
 */
public interface BaseDao {

    public <T> List<T> getAllList(String sql, Class type) throws Exception;
}
