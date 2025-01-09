package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import buildathonproject.DBConnection;

public class ViewStudents extends JFrame implements ActionListener {
    String columnNames[];
    Object[][] data;
    JTable table;
    JButton deleteButton;
    DefaultTableModel model;
    JLabel title;

    public ViewStudents() {
        super("View Students");
        setLayout(new BorderLayout());

        // Title Section
        title = new JLabel("Students", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setBackground(new Color(60, 90, 153));
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Fetch and Display Data
        this.getStudents();
        model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 9) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };

        // JTable Setup
        table = new JTable(model);
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(90, 120, 173));
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 40));
        JScrollPane pane = new JScrollPane(table);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(80);
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(200, 220, 250));
        table.setSelectionForeground(Color.BLACK);
        add(pane, BorderLayout.CENTER);

        // Delete Button
        deleteButton = new JButton("Delete Student");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteButton.setBackground(new Color(200, 50, 50));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setToolTipText("Click to delete the selected student.");
        deleteButton.addActionListener(this);
        deleteButton.setFocusPainted(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Window Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void getStudents() {
        DBConnection c1 = new DBConnection();
        try {
            String q = "SELECT * FROM Student";
            ResultSet rs = c1.s.executeQuery(q);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            int rowCount = 0;

            while (rs.next()) {
                rowCount++;
            }

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
                        byte[] imageBytes = rs.getBytes(columnNames[col]);
                        data[row][col] = getImageIcon(imageBytes);
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
    	if (byteImage == null) {
            // Fallback to a default image if byteImage is null
            try {
                // Attempt to load from the classpath using the correct path
                java.net.URL imgUrl = getClass().getClassLoader().getResource("buildathonproject/icons/placeholder.png");
                if (imgUrl == null) {
                    
                    return new ImageIcon();  // Return an empty icon if not found
                } else {
                    return new ImageIcon(imgUrl);  // Return the placeholder image
                }
            } catch (Exception ex) {
                System.err.println("Error loading placeholder image: " + ex.getMessage());
                return new ImageIcon();  // Return an empty icon on error
            }
        }

        try (InputStream is = new ByteArrayInputStream(byteImage)) {
            BufferedImage bufferedImage = ImageIO.read(is);
            Image scaledImage = bufferedImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException ex) {
            Logger.getLogger(ViewStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteButton) {
            if (table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                String id = model.getValueAt(row, 0).toString();

                DBConnection c1 = new DBConnection();
                try {
                    String q = "DELETE FROM Student WHERE stdID = '" + id + "'";
                    int result = c1.s.executeUpdate(q);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                        dispose();
                        new ViewStudents();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error occurred while deleting the student.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    c1.Close();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            }
        }
    }

    public static void main(String[] args) {
        new ViewStudents();
    }
}
