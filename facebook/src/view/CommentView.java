/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.CommentController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import model.Comment;
/**
 *
 * @author acer
 */
public class CommentView extends JFrame {
    private CommentController controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public CommentView() {
        this.controller = new CommentController(this);

        setTitle("Comment Management");
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Content");
        tableModel.addColumn("Date");
        tableModel.addColumn("User Comment ID");
        tableModel.addColumn("Comment Post ID");

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
                showCreateCommentDialog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUpdateCommentDialog();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (int) tableModel.getValueAt(selectedRow, 0);
                    controller.deleteComment(id);
                }
            }
        });

        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.retrieveComments();
            }
        });

        // Frame settings
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showCreateCommentDialog() {
        JDialog dialog = new JDialog(this, "Create Comment", true);
        dialog.setLayout(new GridLayout(3, 2));

        JTextField contentField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField userCommentIdField = new JTextField();
        JTextField commentPostIdField = new JTextField();

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Comment.CommentBuilder builder = new Comment.CommentBuilder();
                builder.setContent(contentField.getText());
                builder.setDate(dateField.getText());
                builder.setUserCommentId(Integer.parseInt(userCommentIdField.getText()));
                builder.setCommentPostId(Integer.parseInt(commentPostIdField.getText()));
                Comment comment = builder.build();
                controller.createComment(comment);
                dialog.dispose();
            }
        });

        dialog.add(new JLabel("Content:"));
        dialog.add(contentField);
        dialog.add(new JLabel("Date:"));
        dialog.add(dateField);
        dialog.add(new JLabel("User Comment ID:"));
        dialog.add(userCommentIdField);
        dialog.add(new JLabel("Comment Post ID:"));
        dialog.add(commentPostIdField);
        dialog.add(new JLabel());
        dialog.add(createButton);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void showUpdateCommentDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            Comment comment = controller.getComment(id);
            if (comment != null) {
                JDialog dialog = new JDialog(this, "Update Comment", true);
                dialog.setLayout(new GridLayout(3, 2));

                JTextField contentField = new JTextField(comment.getContent());
                JTextField dateField = new JTextField(comment.getDate());
                JTextField userCommentIdField = new JTextField(String.valueOf(comment.getUserCommentId()));
                JTextField commentPostIdField = new JTextField(String.valueOf(comment.getCommentPostId()));

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Comment.CommentBuilder builder = new Comment.CommentBuilder();
                        builder.setId(comment.getId());
                        builder.setContent(contentField.getText());
                        builder.setDate(dateField.getText());
                        builder.setUserCommentId(Integer.parseInt(userCommentIdField.getText()));
                        builder.setCommentPostId(Integer.parseInt(commentPostIdField.getText()));
                        Comment updatedComment = builder.build();
                        controller.updateComment(updatedComment);
                        dialog.dispose();
                    }
                });

                dialog.add(new JLabel("Content:"));
                dialog.add(contentField);
                dialog.add(new JLabel("Date:"));
                dialog.add(dateField);
                dialog.add(new JLabel("User Comment ID:"));
                dialog.add(userCommentIdField);
                dialog.add(new JLabel("Comment Post ID:"));
                dialog.add(commentPostIdField);
                dialog.add(new JLabel());
                dialog.add(updateButton);

                dialog.pack();
                dialog.setLocationRelativeTo(this);
                dialog.setVisible(true);
            }
        }
    }

    public void refreshCommentTable(List<Comment> comments) {
        clearTableModel();
        for (Comment comment : comments) {
            tableModel.addRow(new Object[]{
                    comment.getId(),
                    comment.getContent(),
                    comment.getDate(),
                    comment.getUserCommentId(),
                    comment.getCommentPostId()
            });
        }
    }

    public void showComment(Comment comment) {
        tableModel.addRow(new Object[]{
                comment.getId(),
                comment.getContent(),
                comment.getDate(),
                comment.getUserCommentId(),
                comment.getCommentPostId()
        });
    }

    public void updateComment(Comment comment) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == comment.getId()) {
                tableModel.setValueAt(comment.getContent(), i, 1);
                tableModel.setValueAt(comment.getDate(), i, 2);
                tableModel.setValueAt(comment.getUserCommentId(), i, 3);
                tableModel.setValueAt(comment.getCommentPostId(), i, 4);
                break;
            }
        }
    }

    public void deleteComment(int id) {
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
}