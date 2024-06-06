/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import dao.CommentDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comment;
import util.DatabaseConnection;

/**
 *
 * @author acer
 */
public class CommentDAOImpl implements CommentDAO {
        private Connection connection;

    public CommentDAOImpl() {
        try {
            connection = DatabaseConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void createComment(Comment comment) {
        String sql = "INSERT INTO tbl_comment (content, date, user_comment_id, comment_post_id) VALUES (?, ?, ?, ?)";
        try (
            PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comment.getContent());
            stmt.setString(2, comment.getDate());
            stmt.setInt(3, comment.getUserCommentId());
            stmt.setInt(4, comment.getCommentPostId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Comment getCommentById(int id) {
        String sql = "SELECT * FROM tbl_comment WHERE id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractCommentFromResultSet(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM tbl_comment";
        try (
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Comment comment = extractCommentFromResultSet(rs);
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }

    @Override
    public void updateComment(Comment comment) {
        String sql = "UPDATE tbl_comment SET content = ?, date = ?, user_comment_id = ?, comment_post_id = ? WHERE id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, comment.getContent());
            stmt.setString(2, comment.getDate());
            stmt.setInt(3, comment.getUserCommentId());
            stmt.setInt(4, comment.getCommentPostId());
            stmt.setInt(5, comment.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteComment(int id) {
        String sql = "DELETE FROM tbl_comment WHERE id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Comment extractCommentFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String content = rs.getString("content");
        String date = rs.getString("date");
        int userCommentId = rs.getInt("user_comment_id");
        int commentPostId = rs.getInt("comment_post_id");
        return new Comment.CommentBuilder()
                .setId(id)
                .setContent(content)
                .setDate(date)
                .setUserCommentId(userCommentId)
                .setCommentPostId(commentPostId)
                .build();
    }
}
