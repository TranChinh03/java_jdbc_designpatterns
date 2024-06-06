/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import command.*;
import command.postcategory.CreatePostCategoryCommand;
import command.postcategory.DeletePostCategoryCommand;
import command.postcategory.RetrievePostCategoriesCommand;
import command.postcategory.UpdatePostCategoryCommand;
import dao.DAOFactory;
import dao.PostCategoryDAO;
import model.PostCategory;
import view.PostCategoryView;

import java.util.List;
/**
 *
 * @author acer
 */
public class PostCategoryController {
    private PostCategoryView view;
    private PostCategoryDAO dao;
    private Invoker invoker;

    public PostCategoryController(PostCategoryView view) {
        this.view = view;
        this.dao = DAOFactory.getCategoryDAO();
        this.invoker = new Invoker();
    }

    public void createPostCategory(PostCategory postCategory) {
        Command command = new CreatePostCategoryCommand(dao, postCategory);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.showPostCategory(postCategory);
    }

    public void updatePostCategory(PostCategory postCategory) {
        Command command = new UpdatePostCategoryCommand(dao, postCategory);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.updatePostCategory(postCategory);
    }

    public void deletePostCategory(int id) {
        Command command = new DeletePostCategoryCommand(dao, id);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.deletePostCategory(id);
    }

    public void retrievePostCategories() {
        RetrievePostCategoriesCommand command = new RetrievePostCategoriesCommand(dao);
        invoker.addCommand(command);
        invoker.executeCommands();
        List<PostCategory> postCategories = command.getPostCategories();
        view.refreshPostCategoryTable(postCategories);
    }

    public PostCategory getPostCategory(int id) {
        return dao.getPostCategory(id);
    }
}
