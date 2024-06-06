/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author acer
 */
import model.Post;

import java.util.List;

public interface PostDAO {
    void createPost(Post post);
    void updatePost(Post post);
    void deletePost(int id);
    List<Post> retrievePosts();
    Post getPost(int id);
}
