/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

/**
 *
 * @author acer
 */
import dao.PostDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Post;
import util.DatabaseConnection;

public class PostDAOImpl implements PostDAO {
    private Connection connection;

    public PostDAOImpl() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createPost(Post post) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tbl_post (title, content, created, postcategory_id, user_id) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getCreated());
            statement.setInt(4, post.getPostcategoryId());
            statement.setInt(5, post.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePost(Post post) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE tbl_post SET title = ?, content = ?, created = ?, postcategory_id = ?, user_id = ? WHERE id = ?");
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setString(3, post.getCreated());
            statement.setInt(4, post.getPostcategoryId());
            statement.setInt(5, post.getUserId());
            statement.setInt(6, post.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(int id) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM tbl_comment WHERE (comment_post_id = ?);");
            stm.setInt(1, id);
            stm.execute();
            System.out.println("Da xoa cac comment thuoc ve post");
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tbl_post WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> retrievePosts() {
        List<Post> posts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_post");
            while (resultSet.next()) {
                Post post = new Post.PostBuilder()
                    .setId(resultSet.getInt("id"))
                    .setTitle(resultSet.getString("title"))
                    .setContent(resultSet.getString("content"))
                    .setCreated(resultSet.getString("created"))
                    .setPostcategoryId(resultSet.getInt("postcategory_id"))
                    .setUserId(resultSet.getInt("user_id"))
                    .build();
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post getPost(int id) {
        Post post = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tbl_post WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                post = new Post.PostBuilder()
                    .setId(resultSet.getInt("id"))
                    .setTitle(resultSet.getString("title"))
                    .setContent(resultSet.getString("content"))
                    .setCreated(resultSet.getString("created"))
                    .setPostcategoryId(resultSet.getInt("postcategory_id"))
                    .setUserId(resultSet.getInt("user_id"))
                    .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }
}
