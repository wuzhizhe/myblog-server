package com.mavenwebapp.service;

import com.mavenwebapp.entity.Image;
import com.mavenwebapp.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhangzemu on 2016/11/25.
 */

@Service("imageService")
@Transactional
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    public void insertImage(List<Image> images) {imageMapper.insertImages(images);}

    public Image getImage(String imageId) {
        return imageMapper.getImageById(imageId);
    }
}
