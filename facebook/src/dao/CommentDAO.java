/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Comment;

/**
 *
 * @author acer
 */
public interface CommentDAO {
    void createComment(Comment comment);
    Comment getCommentById(int id);
    List<Comment> getAllComments();
    void updateComment(Comment comment);
    void deleteComment(int id);
}
