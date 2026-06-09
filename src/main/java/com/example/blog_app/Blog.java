package com.example.blog_app;

public class Blog {
    private final int id;
    private final String title;
    private final String text;
    private final String image;
    private final String author;
    private final String created_at;

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
