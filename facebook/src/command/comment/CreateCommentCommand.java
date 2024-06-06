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
import model.Comment;
import dao.CommentDAO;

public class CreateCommentCommand implements Command {
    private CommentDAO commentDAO;
    private Comment comment;

    public CreateCommentCommand(CommentDAO commentDAO, Comment comment) {
        this.commentDAO = commentDAO;
        this.comment = comment;
    }

    @Override
    public void execute() {
        commentDAO.createComment(comment);
    }
}
