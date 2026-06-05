package com.example.blog_app;

public class Blog {
    // private final int id;
    private final String title;
    // private final String text;
    private final String image;
    private final String author;
    // private final String created_at;

    public Blog(String title,String image, String author) {
        this.title = title;
        // this.text = text;
        this.image = image;
        this.author = author;
        // this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    // public String getText() {
    //     return text;
    // }

    public String getImage() {
        return image;
    }

    public String getAuthor() {
        return author;
    }
    // public String getCreated_at() {
    //     return created_at;
    // }

}
