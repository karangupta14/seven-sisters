package com.example.android.sevensisters;

public class Blog {
    String display_name;
    String title;
    String content;

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

    public Blog(String title, String display_name, String content){
        this.title=title;
        this.display_name=display_name;
        this.content=content;
    }
}
