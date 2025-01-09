package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;

import buildathonproject.DBConnection;

public class DeleteSubject extends JFrame implements ActionListener {
    JLabel title, subjectCbLbl;
    JComboBox subjectsCb;
    JButton deleteBtn;
    JPanel middlePanel;

    public DeleteSubject() {
        super("Delete Subject");
        setLayout(new BorderLayout());

        // Title Panel
        title = new JLabel("Delete Subject", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204)); // Dark blue
        title.setOpaque(true);
        title.setBackground(new Color(240, 248, 255)); // Light blue background
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Middle Panel
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBackground(Color.WHITE); // White background for better contrast
        add(middlePanel, BorderLayout.CENTER);

        // Subject Label
        subjectCbLbl = new JLabel("Select Subject:");
        subjectCbLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        subjectCbLbl.setBounds(80, 30, 120, 28);
        middlePanel.add(subjectCbLbl);

        // Subject Dropdown
        subjectsCb = new JComboBox(this.getSubjects());
        subjectsCb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setBounds(210, 30, 180, 28);
        middlePanel.add(subjectsCb);

        // Delete Button
        deleteBtn = new JButton("Delete");
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        deleteBtn.setBackground(new Color(220, 53, 69)); // Red background for caution
        deleteBtn.setForeground(Color.WHITE); // White text for visibility
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBounds(200, 100, 100, 40); // Positioned centrally
        deleteBtn.addActionListener(this);
        middlePanel.add(deleteBtn);

        // Frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLocation(420, 320);
        setVisible(true);
    }

    private String[] getSubjects() {
        String[] subjectsData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "select * from Subjects";
            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while (rs.next())
                rowCount++;
            subjectsData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                subjectsData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectsData;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteBtn) {
            Object selected = subjectsCb.getSelectedItem();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select a subject to delete.", "No Subject Selected", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String subjectName = selected.toString();
            int input = JOptionPane.showConfirmDialog(
                    this,
                    "Deleting this subject will also delete all corresponding courses. Do you want to proceed?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (input == JOptionPane.YES_OPTION) {
                DBConnection c1 = new DBConnection();
                try {
                    String q = "DELETE FROM Subjects WHERE Name = '" + subjectName + "'";
                    int x = c1.s.executeUpdate(q);
                    if (x == 0) {
                        JOptionPane.showMessageDialog(this, "An error occurred while deleting the subject.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Subject deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    c1.Close();
                }
            }
        }
    }

    public static void main(String[] args) {
        new DeleteSubject();
    }
}
