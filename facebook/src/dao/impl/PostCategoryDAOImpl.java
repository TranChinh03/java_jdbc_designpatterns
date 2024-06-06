/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;
import dao.PostCategoryDAO;
import model.PostCategory;

import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author acer
 */
public class PostCategoryDAOImpl implements PostCategoryDAO {
    private Connection connection;

    public PostCategoryDAOImpl() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void createPostCategory(PostCategory postCategory) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tbl_postcategory (name) VALUES (?)");
            statement.setString(1, postCategory.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePostCategory(PostCategory postCategory) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE tbl_postcategory SET name = ? WHERE id = ?");
            statement.setString(1, postCategory.getName());
            statement.setInt(2, postCategory.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePostCategory(int id) {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM tbl_post WHERE (postcategory_id = ?);");
            stm.setInt(1, id);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                int post_id = resultSet.getInt("id");
                PreparedStatement st1 = connection.prepareStatement("DELETE FROM tbl_comment WHERE (comment_post_id = ?);");
                st1.setInt(1, post_id);
                st1.execute();
                System.out.println("Da xoa comment thuoc ve post");
                PreparedStatement st2 = connection.prepareStatement("DELETE FROM tbl_post WHERE (id = ?);");
                st2.setInt(1, post_id);
                st2.execute();
                System.out.println("Da xoa post thuoc ve post");
            }
            PreparedStatement statement = connection.prepareStatement("DELETE FROM tbl_postcategory WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PostCategory> retrievePostCategories() {
        List<PostCategory> postCategories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tbl_postcategory");
            while (resultSet.next()) {
                PostCategory postCategory = new PostCategory.PostCategoryBuilder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .build();
                postCategories.add(postCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postCategories;
    }

    @Override
    public PostCategory getPostCategory(int id) {
        PostCategory postCategory = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tbl_postcategory WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                postCategory = new PostCategory.PostCategoryBuilder()
                    .setId(resultSet.getInt("id"))
                    .setName(resultSet.getString("name"))
                    .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postCategory;
    }
}
