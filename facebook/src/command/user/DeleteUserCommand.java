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
import dao.UserDAO;

public class DeleteUserCommand implements Command {
    private UserDAO userDAO;
    private int userId;

    public DeleteUserCommand(UserDAO userDAO, int userId) {
        this.userDAO = userDAO;
        this.userId = userId;
    }

    @Override
    public void execute() {
        userDAO.deleteUser(userId);
    }
}