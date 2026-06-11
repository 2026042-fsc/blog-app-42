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
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    public BlogController(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }

    // ホームへの遷移
    @GetMapping("/")
    public String home(Model model) {
        // 全件表示
        model.addAttribute("blogs", blogRepository.home());
        return "home";
    }

    // サイトの説明
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    // 詳細ページに飛ぶ
    @GetMapping("/blog/{id}")
    public String detail(@PathVariable int id, Model model) {
        Optional<Blog> BlogOpt = blogRepository.searchById(id);
        if (BlogOpt.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("blog", BlogOpt.get());
        return "blog/detail";
    }

    // 更新
    @GetMapping("/create/createNewBlog")
    public String createNewBlog(Model model) {
        model.addAttribute("blog", new Blog(0, "", "", "", "", ""));
        return "/create/createNewBlog";
    }

    // 編集
    @GetMapping("/blog/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Optional<Blog> BlogOpt = blogRepository.searchById(id);
        if (BlogOpt.isEmpty()) {
            return "redirect:/";
        }
        Blog blog = BlogOpt.get();
        model.addAttribute("blogForm", blog);
        model.addAttribute("blogId", id);

        return "blog/edit";
    }

    // アップデート
    @PostMapping("/blog/{id}")
    public String update(@PathVariable int id, @ModelAttribute Blog blog) {
        blogService.update(id, blog);
        return "redirect:/";
    }

    // blogの保存
    @PostMapping("/create/save")
    public String saveBlog(@ModelAttribute Blog blog) {
        blogRepository.save(blog);
        return "create/complet";
    }

    // blogの保存
    @GetMapping("create/complet")
    public String complet() {
        return "create/complet";
    }

    // 検索
    @GetMapping("/search")
    // required = false 空白でもエラー出さないでねー
    public String blog(@RequestParam(required = false) String keyword, Model model) {
        // なんか書いてあるときは、表示してね。
        if (keyword != null && !keyword.isBlank()) {
            List<Blog> blogs = blogRepository.searchByTitle(keyword);
            model.addAttribute("blogs", blogs);
        }
        model.addAttribute("keyword", keyword);
        return "search";
    }
}