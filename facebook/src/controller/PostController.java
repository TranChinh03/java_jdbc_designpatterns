/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author acer
 */
import command.*;
import command.post.CreatePostCommand;
import command.post.DeletePostCommand;
import command.post.RetrievePostsCommand;
import command.post.UpdatePostCommand;
import dao.DAOFactory;
import dao.PostDAO;
import model.Post;
import view.PostView;

import java.util.List;

public class PostController {
    private PostView view;
    private PostDAO dao;
    private Invoker invoker;

    public PostController(PostView view) {
        this.view = view;
        this.dao = DAOFactory.getPostDAO();
        this.invoker = new Invoker();
    }

    public void createPost(Post post) {
        Command command = new CreatePostCommand(dao, post);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.showPost(post);
    }

    public void updatePost(Post post) {
        Command command = new UpdatePostCommand(dao, post);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.updatePost(post);
    }

    public void deletePost(int id) {
        Command command = new DeletePostCommand(dao, id);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.deletePost(id);
    }

    public void retrievePosts() {
        RetrievePostsCommand command = new RetrievePostsCommand(dao);
        invoker.addCommand(command);
        invoker.executeCommands();
        List<Post> posts = command.getPosts();
        view.refreshPostTable(posts);
    }

    public Post getPost(int id) {
        return dao.getPost(id);
    }
}

