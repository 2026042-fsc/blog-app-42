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
        String spl = "SELECT id,title,text,image,author,created_at FROM blog";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(spl);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Blog blog = new Blog(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getString("created_at")

                );
                blogs.add(blog);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogs;
    }

    public Optional<Blog> searchByid(int id) {
        String sql = "SELECT id,title,text,image,author,created_at FROM blog WHERE id = ?";
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
                            rs.getString("author"),
                            rs.getString("created_at"));
                    return Optional.of(blog);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    public void save(Blog blog) {
        String sql = "INSERT INTO blog (title, text,image,author,created_at) VALUES (?, ? ,? ,? ,NOW())";
        try (Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, blog.getTitle());
            stmt.setString(2, blog.getText());
            stmt.setString(3, blog.getImage());
            stmt.setString(4, blog.getAuthor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Blog> searchByTitle(String keyword) {
        return jdbcClient.sql("SELECT title, text,image FROM blog WHERE title Like :keyword")
                .param("keyword", "%" + keyword + "%")
                .query(blog.class)
                .list();
    }

}
