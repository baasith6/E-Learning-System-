package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import buildathonproject.DBConnection;

public class AdminChangePassword extends JFrame implements ActionListener {
    JLabel titleLabel, newPasswordlbl;
    JPasswordField newPassword;
    JButton updatePassbtn;

    public AdminChangePassword() {
        // Frame properties
        setTitle("Change Password");
        setLayout(null);
        setSize(400, 250);
        setLocation(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light background color
        setResizable(false);

        // Title Label
        titleLabel = new JLabel("Update Password", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        titleLabel.setBounds(20, 10, 360, 30);
        add(titleLabel);

        // New Password Label
        newPasswordlbl = new JLabel("New Password:");
        newPasswordlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newPasswordlbl.setForeground(new Color(51, 51, 51)); // Dark gray
        newPasswordlbl.setBounds(30, 70, 130, 30);
        add(newPasswordlbl);

        // New Password Field
        newPassword = new JPasswordField();
        newPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newPassword.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Blue border
        newPassword.setBounds(170, 70, 200, 40);
        add(newPassword);

        // Update Button
        updatePassbtn = new JButton("Update");
        updatePassbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        updatePassbtn.setBackground(new Color(0, 102, 204)); // Blue background
        updatePassbtn.setForeground(Color.WHITE); // White text
        updatePassbtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
        updatePassbtn.setBounds(125, 150, 150, 40);
        updatePassbtn.addActionListener(this);
        add(updatePassbtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updatePassbtn) {
            String password = String.valueOf(newPassword.getPassword());
            try {
                DBConnection c1 = new DBConnection();

                String q = "update Admin SET password = '" + password + "'"
                        + "Where Adminid ='" + AdminLogin.currentAdminID + "'";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Got some error");
                } else {
                    JOptionPane.showMessageDialog(null, "Your Password Updated Successfully");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AdminChangePassword();
    }
}
