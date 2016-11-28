package com.mavenwebapp.service;

import com.mavenwebapp.entity.Image;
import com.mavenwebapp.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangzemu on 2016/11/25.
 */

@Service("imageService")
@Transactional
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    public void insertImage(Image image) {imageMapper.insertImage(image);}

    public Image getImage(String imageId) {
        return imageMapper.getImage(imageId);
    }
}
