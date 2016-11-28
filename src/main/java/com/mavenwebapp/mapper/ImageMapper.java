package com.mavenwebapp.mapper;

import com.mavenwebapp.entity.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zhangzemu on 2016/11/25.
 */

@Component
public interface ImageMapper {
    @Insert("INSERT into image(" +
            "imageId, name, address, base64Str, type" +
            ") " +
            "values (" +
            "#{imageId}, #{name}, #{address}, #{base64Str}, #{type})")
    public void insertImage(Image image);

    @Select("SELECT * FROM image where imageId = #{imageId}")
    public Image getImage(@Param("imageId") String imageId);
}
