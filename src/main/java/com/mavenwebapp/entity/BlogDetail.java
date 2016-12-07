package com.mavenwebapp.entity;

/**
 * Created by zhangzemu on 2016/11/30.
 */
public class BlogDetail {
    private int blogDetailId;
    private String blogId;
    private String blogText;

    public int getBlogDetailId() {
        return blogDetailId;
    }

    public void setBlogDetailId(int blogDetailId) {
        this.blogDetailId = blogDetailId;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getBlogText() {
        return blogText;
    }

    public void setBlogText(String blogText) {
        this.blogText = blogText;
    }
}
