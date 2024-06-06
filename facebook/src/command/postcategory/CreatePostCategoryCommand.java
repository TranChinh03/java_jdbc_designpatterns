/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.postcategory;
import command.Command;
import dao.PostCategoryDAO;
import model.PostCategory;

/**
 *
 * @author acer
 */
public class CreatePostCategoryCommand implements Command {
    private PostCategoryDAO dao;
    private PostCategory postCategory;

    public CreatePostCategoryCommand(PostCategoryDAO dao, PostCategory postCategory) {
        this.dao = dao;
        this.postCategory = postCategory;
    }
    
    @Override
    public void execute() {
        dao.createPostCategory(postCategory);
    }
}
