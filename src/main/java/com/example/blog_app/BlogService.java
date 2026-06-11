package com.example.blog_app;

import org.springframework.stereotype.Service;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public void update(int id, Blog blog){
        blogRepository.update(id,blog.getTitle(),blog.getText(),blog.getImage(),blog.getAuthor());
    }
}
