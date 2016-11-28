package com.mavenwebapp.entity;

/**
 * Created by zhangzemu on 2016/11/25.
 */
public class Image {

    private String imageId;
    private String name;
    private String address;
    private String base64Str;
    private String type;

    public String getName() {
        return name;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
