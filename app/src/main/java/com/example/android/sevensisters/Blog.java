package com.example.android.sevensisters;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.ArrayList;

public class Blog {
    String display_name;
    String title;
    String content;
    String username;
    boolean visibility;
    ArrayList<Uri> blogImageList;

    public ArrayList<Uri> getBlogImageList() {
        return blogImageList;
    }

    public void setBlogImageList(ArrayList<Uri> blogImageList) {
        this.blogImageList = blogImageList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog(String username, String title, String display_name, String content){
        this.username=username;
        this.title=title;
        this.display_name=display_name;
        this.content=content;
        this.visibility=false;
    }
}
