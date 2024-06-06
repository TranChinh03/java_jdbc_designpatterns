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
import model.User;
import dao.UserDAO;
import model.Comment;

public class UpdateCommentCommand implements Command {
    private CommentDAO commentDAO;
    private Comment comment;

    public UpdateCommentCommand(CommentDAO commentDAO, Comment comment) {
        this.commentDAO = commentDAO;
        this.comment = comment;
    }


    @Override
    public void execute() {
        commentDAO.updateComment(comment);
    }
}
