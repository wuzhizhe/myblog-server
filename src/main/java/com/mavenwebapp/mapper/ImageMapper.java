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

    public void insertImages(List<Image> images);

    public Image getImageById(String imageId);
}
