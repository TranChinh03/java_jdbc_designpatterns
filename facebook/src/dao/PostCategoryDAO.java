/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.PostCategory;
import java.util.List;
/**
 *
 * @author acer
 */
public interface PostCategoryDAO {
    void createPostCategory(PostCategory postCategory);
    void updatePostCategory(PostCategory postCategory);
    void deletePostCategory(int id);
    List<PostCategory> retrievePostCategories();
    PostCategory getPostCategory(int id);
}
