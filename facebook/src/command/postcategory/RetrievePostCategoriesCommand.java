/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.postcategory;
import command.Command;
import dao.PostCategoryDAO;
import java.util.List;
import model.PostCategory;

/**
 *
 * @author acer
 */
public class RetrievePostCategoriesCommand implements Command {
    private PostCategoryDAO dao;
    private List<PostCategory> postCategories;

    public RetrievePostCategoriesCommand(PostCategoryDAO dao) {
        this.dao = dao;
    }

    @Override
    public void execute() {
        postCategories = dao.retrievePostCategories();
    }

    public List<PostCategory> getPostCategories() {
        return postCategories;
    }

    public List<PostCategory> getComments() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
