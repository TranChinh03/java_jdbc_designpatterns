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
public class DeletePostCommand implements Command {
    private PostDAO dao;
    private int id;

    public DeletePostCommand(PostDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void execute() {
        dao.deletePost(id);
    }
}
