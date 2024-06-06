/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author acer
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null) {
            String imagePath = (String) value;
            try {
                ImageIcon icon = new ImageIcon(ImageIO.read(new File(imagePath)));
                label.setIcon(icon);
                label.setText(""); // Clear the text
            } catch (Exception e) {
                label.setIcon(null);
                label.setText("No Image");
            }
        } else {
            label.setText("No Image");
            label.setIcon(null);
        }
        return label;
    }
}