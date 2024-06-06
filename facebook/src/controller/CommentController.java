/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import command.Command;
import command.Invoker;
import command.comment.CreateCommentCommand;
import command.comment.DeleteCommentCommand;
import command.comment.RetrieveCommentCommand;
import command.comment.UpdateCommentCommand;
import dao.CommentDAO;
import dao.DAOFactory;
import java.util.List;
import model.Comment;
import model.PostCategory;
import view.CommentView;
/**
 *
 * @author acer
 */
public class CommentController {
    private CommentView view;
    private CommentDAO commentDAO;
    private Invoker invoker;
    
    public CommentController(CommentView view) {
        this.view = view;
        this.commentDAO = DAOFactory.getCommentDAO();
        this.invoker = new Invoker(); 
    }

    public void createComment(Comment comment) {
        Command command = new CreateCommentCommand(commentDAO, comment);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.showComment(comment);
    }

    public void updateComment(Comment comment) {
        Command command = new UpdateCommentCommand(commentDAO, comment);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.updateComment(comment);
    }

    public void deleteComment(int id) {
        Command command = new DeleteCommentCommand(commentDAO, id);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.deleteComment(id);
    }

    public void retrieveComments() {
        RetrieveCommentCommand command = new RetrieveCommentCommand(commentDAO);
        invoker.addCommand(command);
        invoker.executeCommands();
        List<Comment> comments = command.getComments();
        view.refreshCommentTable(comments);
    }

    public Comment getComment(int id) {
        return commentDAO.getCommentById(id);
    }
}
