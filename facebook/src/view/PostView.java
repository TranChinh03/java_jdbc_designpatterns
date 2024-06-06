/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author acer
 */
import controller.PostController;
import dao.impl.PostDAOImpl;
import model.Post;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class PostView extends JFrame {
    private PostController controller;
    private DefaultTableModel tableModel;
    private JTable table;

    public PostView() {
        super("Post Management");

        // Initialize controller and DAO
        controller = new PostController(this);

        // Initialize components
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Content");
        tableModel.addColumn("Created");
        tableModel.addColumn("Category ID");
        tableModel.addColumn("User ID");

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
                showCreatePostDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdatePostDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    controller.deletePost(id);
                }
            }
        });

        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.retrievePosts();
            }
        });

        // Frame settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showCreatePostDialog() {
        JDialog dialog = new JDialog(this, "Create Post", true);
        dialog.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Title: ");
        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPanel.add(titleLabel, constraints);

        JTextField titleField = new JTextField(20);
        constraints.gridx = 1;
        inputPanel.add(titleField, constraints);

        // Content
        JLabel contentLabel = new JLabel("Content: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(contentLabel, constraints);

        JTextArea contentArea = new JTextArea(5, 20);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        constraints.gridx = 1;
        inputPanel.add(contentScrollPane, constraints);

        // Created
        JLabel createdLabel = new JLabel("Created: ");
        constraints.gridx = 0;
        constraints.gridy = 2;
        inputPanel.add(createdLabel, constraints);

        JTextField createdField = new JTextField(10);
        constraints.gridx = 1;
        inputPanel.add(createdField, constraints);

        // Post Category ID
        JLabel categoryIdLabel = new JLabel("Category ID: ");
        constraints.gridx = 0;
        constraints.gridy = 3;
        inputPanel.add(categoryIdLabel, constraints);

        JTextField categoryIdField = new JTextField(5);
        constraints.gridx = 1;
        inputPanel.add(categoryIdField, constraints);

        // User ID
        JLabel userIdLabel = new JLabel("User ID: ");
        constraints.gridx = 0;
        constraints.gridy = 4;
        inputPanel.add(userIdLabel, constraints);

        JTextField userIdField = new JTextField(5);
        constraints.gridx = 1;
        inputPanel.add(userIdField, constraints);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        // Action listeners
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Post.PostBuilder builder = new Post.PostBuilder();
                builder.setTitle(titleField.getText());
                builder.setContent(contentArea.getText());
                builder.setCreated(createdField.getText());
                builder.setPostcategoryId(Integer.parseInt(categoryIdField.getText()));
                builder.setUserId(Integer.parseInt(userIdField.getText()));
                Post post = builder.build();
                controller.createPost(post);
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    private void showUpdatePostDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Post post = controller.getPost(id);
            if (post != null) {
                JDialog dialog = new JDialog(this, "Update Post", true);
                dialog.setLayout(new BorderLayout());

                JPanel inputPanel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.anchor = GridBagConstraints.WEST;
                constraints.insets = new Insets(10, 10, 10, 10);

                // Title
                JLabel titleLabel = new JLabel("Title: ");
                constraints.gridx = 0;
                constraints.gridy = 0;
                inputPanel.add(titleLabel, constraints);

                JTextField titleField = new JTextField(post.getTitle(), 20);
                constraints.gridx = 1;
                inputPanel.add(titleField, constraints);

                // Content
                JLabel contentLabel = new JLabel("Content: ");
                constraints.gridx = 0;
                constraints.gridy = 1;
                inputPanel.add(contentLabel, constraints);

                JTextArea contentArea = new JTextArea(post.getContent(), 5, 20);
                JScrollPane contentScrollPane = new JScrollPane(contentArea);
                constraints.gridx = 1;
                inputPanel.add(contentScrollPane, constraints);

                // Created
                JLabel createdLabel = new JLabel("Created: ");
                constraints.gridx = 0;
                constraints.gridy = 2;
                inputPanel.add(createdLabel, constraints);

                JTextField createdField = new JTextField(post.getCreated(), 10);
                constraints.gridx = 1;
                inputPanel.add(createdField, constraints);

                // Post Category ID
                JLabel categoryIdLabel = new JLabel("Category ID: ");
                constraints.gridx = 0;
                constraints.gridy = 3;
                inputPanel.add(categoryIdLabel, constraints);

                JTextField categoryIdField = new JTextField(String.valueOf(post.getPostcategoryId()), 5);
                constraints.gridx = 1;
                inputPanel.add(categoryIdField, constraints);

                // User ID
                JLabel userIdLabel = new JLabel("User ID: ");
                constraints.gridx = 0;
                constraints.gridy = 4;
                inputPanel.add(userIdLabel, constraints);

                JTextField userIdField = new JTextField(String.valueOf(post.getUserId()), 5);
                constraints.gridx = 1;
                inputPanel.add(userIdField, constraints);

                // Button Panel
                JPanel buttonPanel = new JPanel();
                JButton updateButton = new JButton("Update");
                JButton cancelButton = new JButton("Cancel");
                buttonPanel.add(updateButton);
                buttonPanel.add(cancelButton);

                // Action listeners
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Post.PostBuilder builder = new Post.PostBuilder();
                        builder.setId(post.getId());
                        builder.setTitle(titleField.getText());
                        builder.setContent(contentArea.getText());
                        builder.setCreated(createdField.getText());
                        builder.setPostcategoryId(Integer.parseInt(categoryIdField.getText()));
                        builder.setUserId(Integer.parseInt(userIdField.getText()));
                        Post updatedPost = builder.build();
                        controller.updatePost(updatedPost);
                        dialog.dispose();
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                    }
                });

                dialog.add(inputPanel, BorderLayout.CENTER);
                dialog.add(buttonPanel, BorderLayout.SOUTH);

                dialog.pack();
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            }
        }
    }


    public void refreshPostTable(List<Post> posts) {
        clearTableModel();
        for (Post post : posts) {
            tableModel.addRow(new Object[]{
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getCreated(),
                    post.getPostcategoryId(),
                    post.getUserId()
            });
        }
    }

    public void showPost(Post post) {
        tableModel.addRow(new Object[]{
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreated(),
                post.getPostcategoryId(),
                post.getUserId()
        });
    }

    public void updatePost(Post post) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == post.getId()) {
                tableModel.setValueAt(post.getTitle(), i, 1);
                tableModel.setValueAt(post.getContent(), i, 2);
                tableModel.setValueAt(post.getCreated(), i, 3);
                tableModel.setValueAt(post.getPostcategoryId(), i, 4);
                tableModel.setValueAt(post.getUserId(), i, 5);
                break;
            }
        }
    }

    public void deletePost(int id) {
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
                new PostView();
            }
        });
    }
}
