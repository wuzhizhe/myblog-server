package com.mavenwebapp.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Map;

/**
 * Created by zhangzemu on 2016/12/7.
 * 为了应对request.getParameter方法失效，用getReader方法进行处理获取前端的POST数据.
 */
public class RequestUtil {
    public static Map<String, Object> getRequestBody(HttpServletRequest request) {
        Map<String, Object> json = null;
        StringBuffer sb = new StringBuffer();
        String line = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                sb.append(line);
            json = mapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return json;
        }
    }
}
