package com.mavenwebapp.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenwebapp.entity.Image;
import com.mavenwebapp.service.ImageService;
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

    public static String webRoot = System.getProperty("pwd");
    BASE64Decoder decoder = new BASE64Decoder();

    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Image[] images = mapper.readValue(request.getParameter("images"), Image[].class);
            saveImageFiles(images);
            List<Image> imageList = Arrays.asList(images);
            saveImage(imageList);
            map.put("data", images);
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

    public void saveImageFiles(Image[] images) {
        try {
            for (int i = 0; i < images.length; i++) {
                String uuid = UUID.randomUUID().toString();
                BufferedImage image = null;
                Image tempImg = images[i];
                String base64str = tempImg.getBase64Str().split(",")[1];
                byte[] imageByte = DatatypeConverter.parseBase64Binary(base64str);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(new ByteArrayInputStream(imageByte));
                bis.close();
                String imageType = tempImg.getType().split("/")[1];
                Path path = Paths.get(webRoot + "images");
                if (Files.notExists(path)) {
                    Files.createDirectories(path);
                }
                String filename = webRoot + "images" + "\\" + uuid +"." + imageType;
                tempImg.setAddress("images/"+ uuid +"." + imageType);
                tempImg.setImageId(uuid);
                File outputfile = new File(filename);
                ImageIO.write(image, imageType, outputfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
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

