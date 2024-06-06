/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
package command.postcategory;
import command.Command;
import dao.PostCategoryDAO;
import model.PostCategory;

public class DeletePostCategoryCommand implements Command {
    private PostCategoryDAO dao;
    private int id;

    public DeletePostCategoryCommand(PostCategoryDAO dao, int id) {
        this.dao = dao;
        this.id = id;
    }

    @Override
    public void execute() {
        dao.deletePostCategory(id);
    }
}