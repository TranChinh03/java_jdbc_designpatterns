/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.post;

/**
 *
 * @author acer
 */
import command.Command;
import dao.PostDAO;
import model.Post;

public class CreatePostCommand implements Command {
    private PostDAO dao;
    private Post post;

    public CreatePostCommand(PostDAO dao, Post post) {
        this.dao = dao;
        this.post = post;
    }

    @Override
    public void execute() {
        dao.createPost(post);
    }
}
