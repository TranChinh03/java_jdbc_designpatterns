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

public class UpdateUserCommand implements Command {
    private UserDAO userDAO;
    private User user;

    public UpdateUserCommand(UserDAO userDAO, User user) {
        this.userDAO = userDAO;
        this.user = user;
    }

    @Override
    public void execute() {
        userDAO.updateUser(user);
    }
}
