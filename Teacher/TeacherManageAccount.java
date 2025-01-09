package buildathonproject.Teacher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import buildathonproject.DBConnection;

public class TeacherManageAccount extends JFrame implements ActionListener {
    JPanel panel;
    JLabel title;
    JButton b1, b2, b3, b4, b5;
    FileInputStream fis = null;
    File f = null;

    public TeacherManageAccount() {
        super("Manage Teacher Account");

        // Main Panel
        panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Light background color

        // Title
        title = new JLabel("Manage Account", JLabel.CENTER);
        title.setFont(new Font("Tahoma", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 204)); // Blue color for the title
        panel.add(title, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonsPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
        buttonsPanel.setBackground(new Color(240, 248, 255)); // Same background as main panel

        b1 = createStyledButton("Change Password");
        b2 = createStyledButton("Change Name");
        b3 = createStyledButton("Change Email");
        b4 = createStyledButton("Change Profile");
        b5 = createStyledButton("Exit");

        buttonsPanel.add(b1);
        buttonsPanel.add(b2);
        buttonsPanel.add(b3);
        buttonsPanel.add(b4);
        buttonsPanel.add(b5);

        // Action Listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        panel.add(buttonsPanel, BorderLayout.CENTER);

        // Final Frame Settings
        add(panel);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light background color
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Tahoma", Font.PLAIN, 18)); // Modern font
        button.setBackground(new Color(51, 51, 51)); // Dark gray background
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51), 2));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(105, 105, 105)); // Lighter shade of gray on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(51, 51, 51)); // Original dark gray
            }
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            new TeacherChangePassword();
        } else if (ae.getSource() == b2) {
            new TeacherChangeName();
        } else if (ae.getSource() == b3) {
            new TeacherChangeEmail();
        } else if (ae.getSource() == b4) {
            String fname = null;
            JFileChooser fchoser = new JFileChooser();
            fchoser.showOpenDialog(null);
            f = fchoser.getSelectedFile();
            fname = f.getAbsolutePath();
            File image = new File(fname);
            try {
                fis = new FileInputStream(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TeacherManageAccount.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                DBConnection c1 = new DBConnection();
                PreparedStatement ps = c1.c.prepareStatement("update Teacher SET picture =? Where teacherID =?");
                ps.setBinaryStream(1, fis, (int) f.length());
                ps.setInt(2, TeacherLogin.currentTeacherID);
                int x = ps.executeUpdate();
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Got Some Error");
                } else {
                    JOptionPane.showMessageDialog(null, "Profile updated Successfully!");
                    dispose();
                }
                ps.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else if (ae.getSource() == b5) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new TeacherManageAccount();
    }
}
