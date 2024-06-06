/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author acer
 */
import command.Command;
import command.navigation.OpenCategoryViewCommand;
import command.navigation.OpenCommentViewCommand;
import command.navigation.OpenPostViewCommand;
import command.navigation.OpenUserViewCommand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationFrame extends JFrame {

    public NavigationFrame() {
        setTitle("Navigation Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null); // Center on screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton postButton = new JButton("Open Post View");
        JButton commentButton = new JButton("Open Comment View");
        JButton categoryButton = new JButton("Open Category View");
        JButton userButton = new JButton("Open User View");

        panel.add(postButton);
        panel.add(commentButton);
        panel.add(categoryButton);
        panel.add(userButton);

        postButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command openPostCommand = new OpenPostViewCommand();
                openPostCommand.execute();
            }
        });

        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command openCommentCommand = new OpenCommentViewCommand();
                openCommentCommand.execute();
            }
        });

        categoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command openCategoryCommand = new OpenCategoryViewCommand();
                openCategoryCommand.execute();
            }
        });

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command openUserCommand = new OpenUserViewCommand();
                openUserCommand.execute();
            }
        });

        add(panel);
    }
}

