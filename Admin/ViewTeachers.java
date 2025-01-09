package buildathonproject.Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import buildathonproject.DBConnection;

public class ViewTeachers extends JFrame implements ActionListener {
    String columnNames[];
    Object[][] data;
    JTable table;
    JButton deleteButton;
    DefaultTableModel model;
    JLabel title;

    public ViewTeachers() {
        super("View Teachers");
        setLayout(new BorderLayout());

        // Improved Header with Gradient Background
        title = new JLabel("Teachers", JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                if (g instanceof Graphics2D) {
                    final int width = getWidth();
                    final int height = getHeight();
                    Graphics2D g2d = (Graphics2D) g;
                    GradientPaint gp = new GradientPaint(0, 0, Color.BLUE, width, height, Color.CYAN);
                    g2d.setPaint(gp);
                    g2d.fillRect(0, 0, width, height);
                }
                super.paintComponent(g);
            }
        };
        title.setFont(new Font("Tahoma", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setOpaque(false); // Gradient replaces the background color
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Retrieve and populate table data (functional code unchanged)
        this.getTeachers();

        // Improved Table Design
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 9 ? ImageIcon.class : Object.class;
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(51, 153, 255));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(96);
        table.setSelectionBackground(new Color(173, 216, 230));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowVerticalLines(false);

        // Add a JScrollPane for the table
        JScrollPane pane = new JScrollPane(table);
        pane.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(pane, BorderLayout.CENTER);

        // Styled Delete Button
        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        deleteButton.setBackground(new Color(255, 69, 0));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        deleteButton.addActionListener(this);
        deleteButton.setToolTipText("Click to delete the selected record.");
        add(deleteButton, BorderLayout.SOUTH);

        // Frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void getTeachers() {
        DBConnection c1 = new DBConnection();
        try {
            String q = "SELECT * FROM Teacher";
            ResultSet rs = c1.s.executeQuery(q);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            int rowCount = 0;

            while (rs.next())
                rowCount++;

            columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }

            data = new Object[rowCount][columnCount];
            int row = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int col = 0; col < columnCount; col++) {
                    if (columnNames[col].equalsIgnoreCase("picture")) {
                        byte[] bytImage = rs.getBytes(columnNames[col]);
                        data[row][col] = getImageIcon(bytImage);
                    } else {
                        data[row][col] = rs.getString(columnNames[col]);
                    }
                }
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c1.Close();
        }
    }

    private ImageIcon getImageIcon(byte[] byteImage) {
        try (InputStream is = new ByteArrayInputStream(byteImage)) {
            BufferedImage bufferedImage = ImageIO.read(is);
            ImageIcon icon = new ImageIcon(bufferedImage);
            Image scaledImage = icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException ex) {
            Logger.getLogger(ViewTeachers.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteButton) {
            if (table.getSelectedRow() != -1) {
                int rowNum = table.getSelectedRow();
                String id = model.getValueAt(rowNum, 0).toString();

                DBConnection c1 = new DBConnection();
                try {
                    String q = "DELETE FROM Teacher WHERE teacherID = '" + id + "'";
                    int x = c1.s.executeUpdate(q);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(null, "Error Occurred");
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                        dispose();
                        new ViewTeachers();
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                } finally {
                    c1.Close();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            }
        }
    }

    public static void main(String[] args) {
        new ViewTeachers();
    }
}
