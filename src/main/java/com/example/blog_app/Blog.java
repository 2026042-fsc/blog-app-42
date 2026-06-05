package com.example.blog_app;

public class Blog {
    private final String title;
    private final String text;
    private final String image;
    private final String auther;

    public Blog(String title, String text, String image, String auther) {
        this.title = "";
        this.text = "";
        this.image = "";
        this.auther = "";
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getImage() {
        return image;
    }

    public String getAuther() {
        return auther;
    }

}
