package com.example.blog_app;

public class Blog {
    private  int id;
    private  String title;
    private  String text;
    private  String image;
    private  String author;
    private  String created_at;

    public Blog(int id, String title, String text, String image, String author, String created_at) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.image = image;
        this.author = author;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getAuthor() {
        return author;
    }

    public String getCreated_at() {
        return created_at;
    }

}
