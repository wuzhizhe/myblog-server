package com.mavenwebapp.utils;

import com.mavenwebapp.entity.Image;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by zhangzemu on 2016/12/23.
 */
public class SaveImageUtil {
    public static String webRoot = System.getProperty("pwd");
    public static void saveImageFiles(Image[] images, String dirName) {
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
                Path path = Paths.get(webRoot + dirName);
                if (Files.notExists(path)) {
                    Files.createDirectories(path);
                }
                String filename = webRoot + dirName + "\\" + uuid +"." + imageType;
                tempImg.setAddress(dirName + "/"+ uuid +"." + imageType);
                tempImg.setImageId(uuid);
                File outputfile = new File(filename);
                ImageIO.write(image, imageType, outputfile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
