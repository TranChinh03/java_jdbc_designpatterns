/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author acer
 */

import controller.UserController;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.TableColumn;

public class UserView extends JFrame {
    private UserController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public UserView() {
        controller = new UserController(this);

        setTitle("User Management");
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Address");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Email");
        tableModel.addColumn("Gender (1: Male, 0: Female");
        tableModel.addColumn("Profile Image");

        table = new JTable(tableModel);
        TableColumn imageColumn = table.getColumnModel().getColumn(6);
        imageColumn.setCellRenderer(new ImageRenderer());

        JScrollPane scrollPane = new JScrollPane(table);

        JButton createButton = new JButton("Create");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton retrieveButton = new JButton("Retrieve");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(retrieveButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateUserDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateUserDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    controller.deleteUser(id);
                }
            }
        });

        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.retrieveUsers();
            }
        });

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showCreateUserDialog() {
        JDialog dialog = new JDialog(this, "Create User", true);
        dialog.setLayout(new GridLayout(8, 2));

        JTextField usernameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField profileImageField = new JTextField();

        JButton chooseImageButton = new JButton("Choose Image");
        chooseImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(dialog);
                if (result == JFileChooser.APPROVE_OPTION) {
                    profileImageField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User.UserBuilder()
                        .setUsername(usernameField.getText())
                        .setAddress(addressField.getText())
                        .setPhone(phoneField.getText())
                        .setEmail(emailField.getText())
                        .setGender(Byte.parseByte(genderField.getText()))
                        .setProfileImage(profileImageField.getText())
                        .build();
                controller.createUser(user);
                dialog.dispose();
            }
        });

        dialog.add(new JLabel("Username:"));
        dialog.add(usernameField);
        dialog.add(new JLabel("Address:"));
        dialog.add(addressField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Gender:"));
        dialog.add(genderField);
        dialog.add(new JLabel("Profile Image:"));
        dialog.add(profileImageField);
        dialog.add(chooseImageButton);
        dialog.add(createButton);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showUpdateUserDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            User user = controller.getUser(id);
            if (user != null) {
                JDialog dialog = new JDialog(this, "Update User", true);
                dialog.setLayout(new GridLayout(8, 2));

                JTextField usernameField = new JTextField(user.getUsername());
                JTextField addressField = new JTextField(user.getAddress());
                JTextField phoneField = new JTextField(user.getPhone());
                JTextField emailField = new JTextField(user.getEmail());
                JTextField genderField = new JTextField(String.valueOf(user.getGender()));
                JTextField profileImageField = new JTextField(user.getProfileImage());

                JButton chooseImageButton = new JButton("Choose Image");
                chooseImageButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
                        int result = fileChooser.showOpenDialog(dialog);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            profileImageField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                        }
                    }
                });

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        User updatedUser = new User.UserBuilder()
                                .setId(user.getId())
                                .setUsername(usernameField.getText())
                                .setAddress(addressField.getText())
                                .setPhone(phoneField.getText())
                                .setEmail(emailField.getText())
                                .setGender(Byte.parseByte(genderField.getText()))
                                .setProfileImage(profileImageField.getText())
                                .build();
                        controller.updateUser(updatedUser);
                        dialog.dispose();
                    }
                });

                dialog.add(new JLabel("Username:"));
                dialog.add(usernameField);
                dialog.add(new JLabel("Address:"));
                dialog.add(addressField);
                dialog.add(new JLabel("Phone:"));
                dialog.add(phoneField);
                dialog.add(new JLabel("Email:"));
                dialog.add(emailField);
                dialog.add(new JLabel("Gender:"));
                dialog.add(genderField);
                dialog.add(new JLabel("Profile Image:"));
                dialog.add(profileImageField);
                dialog.add(chooseImageButton);
                dialog.add(updateButton);

                dialog.pack();
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            }
        }
    }

    public void refreshUserTable(List<User> users) {
        clearTableModel();
        for (User user : users) {
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getAddress(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getGender(),
                    user.getProfileImage()
            });
        }
    }

    private void clearTableModel() {
        tableModel.setRowCount(0);
    }

    public void showUser(User user) {
        tableModel.addRow(new Object[]{
                user.getId(),
                user.getUsername(),
                user.getAddress(),
                user.getPhone(),
                user.getEmail(),
                user.getGender(),
                user.getProfileImage()
        });
    }

    public void updateUser(User user) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == user.getId()) {
                tableModel.setValueAt(user.getUsername(), i, 1);
                tableModel.setValueAt(user.getAddress(), i, 2);
                tableModel.setValueAt(user.getPhone(), i, 3);
                tableModel.setValueAt(user.getEmail(), i, 4);
                tableModel.setValueAt(user.getGender(), i, 5);
                tableModel.setValueAt(user.getProfileImage(), i, 6);
                break;
            }
        }
    }

    public void deleteUser(int id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == id) {
                tableModel.removeRow(i);
                break;
            }
        }
    }
}