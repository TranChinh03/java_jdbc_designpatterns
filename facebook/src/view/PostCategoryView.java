/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author acer
 */
import controller.PostCategoryController;
import dao.impl.PostCategoryDAOImpl;
import model.PostCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class PostCategoryView extends JFrame {
    private PostCategoryController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public PostCategoryView() {
        controller = new PostCategoryController(this);

        setTitle("Post Category Management");
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        JButton createButton = new JButton("Create");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton retrieveButton = new JButton("Retrieve");

        // Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(retrieveButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Button listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreatePostCategoryDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdatePostCategoryDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    controller.deletePostCategory(id);
                }
            }
        });

        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.retrievePostCategories();
            }
        });

        // Frame settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showCreatePostCategoryDialog() {
        JDialog dialog = new JDialog(this, "Create Post Category", true);
        dialog.setLayout(new GridLayout(2, 2));

        JTextField nameField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostCategory.PostCategoryBuilder builder = new PostCategory.PostCategoryBuilder();
                builder.setName(nameField.getText());
                PostCategory postCategory = builder.build();
                controller.createPostCategory(postCategory);
                dialog.dispose();
            }
        });

        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel());
        dialog.add(createButton);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showUpdatePostCategoryDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            PostCategory postCategory = controller.getPostCategory(id);
            if (postCategory != null) {
                JDialog dialog = new JDialog(this, "Update Post Category", true);
                dialog.setLayout(new GridLayout(2, 2));

                JTextField nameField = new JTextField(postCategory.getName());

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PostCategory.PostCategoryBuilder builder = new PostCategory.PostCategoryBuilder();
                        builder.setId(postCategory.getId());
                        builder.setName(nameField.getText());
                        PostCategory updatedPostCategory = builder.build();
                        controller.updatePostCategory(updatedPostCategory);
                        dialog.dispose();
                    }
                });

                dialog.add(new JLabel("Name:"));
                dialog.add(nameField);
                dialog.add(new JLabel());
                dialog.add(updateButton);

                dialog.pack();
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            }
        }
    }

    public void refreshPostCategoryTable(List<PostCategory> postCategories) {
        clearTableModel();
        for (PostCategory postCategory : postCategories) {
            tableModel.addRow(new Object[]{
                    postCategory.getId(),
                    postCategory.getName()
            });
        }
    }

    public void showPostCategory(PostCategory postCategory) {
        tableModel.addRow(new Object[]{
                postCategory.getId(),
                postCategory.getName()
        });
    }

    public void updatePostCategory(PostCategory postCategory) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == postCategory.getId()) {
                tableModel.setValueAt(postCategory.getName(), i, 1);
                break;
            }
        }
    }

    public void deletePostCategory(int id) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == id) {
                tableModel.removeRow(i);
                break;
            }
        }
    }

    private void clearTableModel() {
        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PostCategoryView();
            }
        });
    }
}
