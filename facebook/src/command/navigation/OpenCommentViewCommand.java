/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package command.navigation;

/**
 *
 * @author acer
 */
import command.Command;
import javax.swing.*;
import view.CommentView;

public class OpenCommentViewCommand implements Command {
    @Override
    public void execute() {
        SwingUtilities.invokeLater(() -> {
            JFrame commentFrame = new CommentView();
            commentFrame.setVisible(true);
        });
    }
}