package com.example.blog_app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class BlogRepojitory {
    private DataSource dataSource;

    public BlogRepojitory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Blog> home() {
        List<Blog> blogs = new ArrayList<>();
        String spl = "SELECT id,title,text,image,author FROM blog";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(spl);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getString("image"),
                        rs.getString("author"));
                blogs.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogs;
    }

    public Optional<Blog> searchByid(int id) {
        String sql = "SELECT id,title,text,image,author FROM blog WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Blog blog = new Blog(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("text"),
                            rs.getString("image"),
                            rs.getString("author"));
                    return Optional.of(blog);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    // public List<Blog> (){

    }
// }

// public Optinal<Blog> findByID(Long id){
// return jdbcCilent.sql("SQLの内容 WHERE id == :id");
// .param("id",id)
// .query(Blog.class)
