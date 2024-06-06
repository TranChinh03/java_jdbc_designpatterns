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
import view.UserView;

public class RetrieveUsersCommand implements Command {
    private UserDAO userDAO;
    private UserView view;

    public RetrieveUsersCommand(UserDAO userDAO, UserView view) {
        this.userDAO = userDAO;
        this.view = view;
    }

    @Override
    public void execute() {
        view.refreshUserTable(userDAO.getAllUsers());
    }
}
