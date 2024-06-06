/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author acer
 */
import dao.impl.CommentDAOImpl;
import dao.impl.PostCategoryDAOImpl;
import dao.impl.PostDAOImpl;
import dao.impl.UserDAOImpl;

// Factory Method patterns
public class DAOFactory {
    public static UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
    
    public static PostCategoryDAO getCategoryDAO() {
        return new PostCategoryDAOImpl();
    }
    
    public static PostDAO getPostDAO() {
        return new PostDAOImpl();
    }
    
    public static CommentDAO getCommentDAO() {
        return new CommentDAOImpl();
    }
}
