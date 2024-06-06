/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.comment;

/**
 *
 * @author acer
 */
import command.user.*;
import command.Command;
import dao.CommentDAO;
import dao.UserDAO;
import model.Comment;

public class DeleteCommentCommand implements Command {
    private CommentDAO commentDAO;
    private int commentId;

    public DeleteCommentCommand(CommentDAO commentDAO, int id) {
        this.commentDAO = commentDAO;
        this.commentId = id;
    }

    @Override
    public void execute() {
        commentDAO.deleteComment(commentId);
    }
}