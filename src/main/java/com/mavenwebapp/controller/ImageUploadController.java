package com.mavenwebapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenwebapp.entity.Image;
import com.mavenwebapp.service.ImageService;
import com.mavenwebapp.utils.RequestUtil;
import com.mavenwebapp.utils.SaveImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by zhangzemu on 2016/11/18.
 */

@Controller
@RequestMapping(
        value = {"/image"},
        method = {RequestMethod.POST, RequestMethod.GET}
)
public class ImageUploadController {

    @Autowired
    ImageService imageService;

    BASE64Decoder decoder = new BASE64Decoder();

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
//            Map<String, Object> mapjson = mapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>() {});
//            Map<String, Object> mapjson = RequestUtil.getRequestBody(request);
            Image[] images = mapper.readValue(request.getParameter("images"), Image[].class);
            String dir = request.getParameter("dir").toString();
            String dirName = (dir == null || "".equals(dir) ) ? "images" : dir;
            SaveImageUtil.saveImageFiles(images, dirName);
            List<Image> imageList = Arrays.asList(images);
            saveImage(imageList);
            map.put("data", images);
            map.put("success", true);
        } catch (JsonMappingException e) {
            map.put("success", false);
            e.printStackTrace();
        } catch (Exception e) {
            map.put("success", false);
            e.printStackTrace();
        } finally {
            return map;
        }
    }



    public void saveImage(List<Image> images) {
        try {
            imageService.insertImage(images);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("end one upload!");
        }
    }
}

