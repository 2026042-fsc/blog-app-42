package com.example.blog_app;

import java.util.List;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class BlogRepository {
    private final JdbcClient jdbcClient;

    public BlogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    //ホームでの全件表示
    public List<Blog> home() {
        return jdbcClient.sql("SELECT id,title,text,image,author,created_at FROM blog")
                .query(Blog.class)
                .list();
    }

        //詳細ページのID取得
    public Optional searchById(int id) {
       return jdbcClient.sql("SELECT id,title,text,image,author,created_at FROM blog WHERE id = :id")
                .param("id", id)
                .query(Blog.class)
                .optional();
    }

        //フォームの作成
    public void save(Blog blog) {
        jdbcClient.sql(
                "INSERT INTO blog (id,title, text,image,author,created_at) VALUES (:title, :text ,:image ,:author ,NOW())")
                .param("title", blog.getTitle())
                .param("text", blog.getText())
                .param("image", blog.getImage())
                .param("author", blog.getAuthor())
                .update();
    }

            //検索
    public List<Blog> searchByTitle(String keyword) {
        return jdbcClient.sql("SELECT id,title, text,image,author,created_at FROM blog WHERE title Like :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(Blog.class)
                .list();
    }

}
