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
import java.util.List;
import model.Post;
public class RetrievePostsCommand implements Command {
    private PostDAO dao;
    private List<Post> posts;

    public RetrievePostsCommand(PostDAO dao) {
        this.dao = dao;
    }

    @Override
    public void execute() {
        posts = dao.retrievePosts();
    }

    public List<Post> getPosts() {
        return posts;
    }
}
