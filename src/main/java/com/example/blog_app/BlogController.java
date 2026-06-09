package com.example.blog_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class BlogController {
    private final BlogRepojitory blogRepojitory;

    public BlogController(BlogRepojitory blogRepojitory) {
        this.blogRepojitory = blogRepojitory;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("blogs", blogRepojitory.home());
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

    @GetMapping("/blog/{id}")
    public String detail(@PathVariable int id, Model model) {
        Optional<Blog> BlogOpt = blogRepojitory.searchByid(id);
        if (BlogOpt.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("blog", BlogOpt.get());
        return "blog/detail";
    }

    @GetMapping("/create/createNewBlog")
    public String createNewBlog(Model model) {
        model.addAttribute("blog", new Blog(0, "", "", "", "",""));
        return "/create/createNewBlog";
    }

    @PostMapping("/create/save")
    public String saveBlog(@ModelAttribute Blog blog) {
        blogRepojitory.save(blog);
        return "create/complet";
    }
    @GetMapping("create/complet")
    public String complet() {
        return "create/complet";
    }
     @GetMapping("/search")
    public String book(@RequestParam(required = false) String keyword, Model model) {
        List<Book> books = bookService.search(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);

        return "search";
}
