package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("/ID_1")
    public String ID_1() {
        return "ID_1";
    }

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable Long id) {
        return "blog/detail";
    }

    @GetMapping("/create/createNewBlog")
    public String createNewBlog() {
        return "/create/createNewBlog";
    }

}
