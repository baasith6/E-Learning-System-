package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import buildathonproject.DBConnection;

public class AdminChangeEmail extends JFrame implements ActionListener {
    JLabel newEmaillbl, titleLabel;
    JTextField newEmail;
    JButton updateEmailbtn;

    public AdminChangeEmail() {
        // Frame properties
        setTitle("Change Email");
        setLayout(null);
        setSize(400, 250);
        setLocation(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light background color
        setResizable(false);

        // Title Label
        titleLabel = new JLabel("Update Your Email", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        titleLabel.setBounds(20, 10, 360, 30);
        add(titleLabel);

        // Email Label
        newEmaillbl = new JLabel("New Email:");
        newEmaillbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newEmaillbl.setForeground(new Color(51, 51, 51)); // Dark gray
        newEmaillbl.setBounds(30, 70, 100, 30);
        add(newEmaillbl);

        // Email Text Field
        newEmail = new JTextField();
        newEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newEmail.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Blue border
        newEmail.setBounds(140, 70, 220, 40);
        add(newEmail);

        // Update Button
        updateEmailbtn = new JButton("Update");
        updateEmailbtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateEmailbtn.setBackground(new Color(0, 102, 204)); // Blue background
        updateEmailbtn.setForeground(Color.WHITE); // White text
        updateEmailbtn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
        updateEmailbtn.setBounds(120, 140, 150, 40);
        updateEmailbtn.addActionListener(this);
        add(updateEmailbtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateEmailbtn) {
            String EmailID = newEmail.getText();
            try {
                DBConnection c1 = new DBConnection();

                String q = "UPDATE Admin SET Email_ID = '" + EmailID + "'"
                        + " WHERE Adminid = '" + AdminLogin.currentAdminID + "'";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Got some error");
                } else {
                    JOptionPane.showMessageDialog(null, "Your Email Updated Successfully");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AdminChangeEmail();
    }
}
