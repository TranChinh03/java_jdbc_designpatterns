/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.user;

/**
 *
 * @author acer
 */

import command.Command;
import model.User;
import dao.UserDAO;

public class CreateUserCommand implements Command {
    private UserDAO userDAO;
    private User user;

    public CreateUserCommand(UserDAO userDAO, User user) {
        this.userDAO = userDAO;
        this.user = user;
    }

    @Override
    public void execute() {
        userDAO.createUser(user);
    }
}
