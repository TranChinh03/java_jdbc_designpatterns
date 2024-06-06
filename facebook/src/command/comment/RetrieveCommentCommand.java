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
import java.util.List;
import model.Comment;
import view.UserView;

public class RetrieveCommentCommand implements Command {
    private CommentDAO commentDAO;
    private List<Comment> comments;

    public RetrieveCommentCommand(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    @Override
    public void execute() {
        comments = commentDAO.getAllComments();
    }
    
    public List<Comment> getComments() {
        return comments;
}
}
