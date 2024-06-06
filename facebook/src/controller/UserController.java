/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import command.Command;
import command.Invoker;
import command.user.CreateUserCommand;
import command.user.DeleteUserCommand;
import command.user.RetrieveUsersCommand;
import command.user.UpdateUserCommand;
import dao.DAOFactory;
import dao.UserDAO;
import model.User;
import view.UserView;

import java.util.List;
/**
 *
 * @author acer
 */
public class UserController {
    private UserView view;
    private UserDAO userDAO;
    private Invoker invoker;

    public UserController(UserView view) {
        this.view = view;
        this.userDAO = DAOFactory.getUserDAO();
        this.invoker = new Invoker(); 
    }

    public void createUser(User user) {
        Command command = new CreateUserCommand(userDAO, user);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.showUser(user);
    }

    public User getUser(int id) {
        return userDAO.getUser(id);
    }

    public void retrieveUsers() {
        Command command = new RetrieveUsersCommand(userDAO, view);
        invoker.addCommand(command);
        invoker.executeCommands();
    }

    public void updateUser(User user) {
        Command command = new UpdateUserCommand(userDAO, user);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.updateUser(user);
    }

    public void deleteUser(int id) {
        Command command = new DeleteUserCommand(userDAO, id);
        invoker.addCommand(command);
        invoker.executeCommands();
        view.deleteUser(id);
    }
}
