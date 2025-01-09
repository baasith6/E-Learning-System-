package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import buildathonproject.DBConnection;
import buildathonproject.Admin.AdminLogin;

public class AdminDeleteAccount extends JFrame implements ActionListener {
    JLabel warningLabel;
    JButton yesButton, noButton, cancelButton;
    int input;

    public AdminDeleteAccount() {
        // Frame properties
        setTitle("Delete Account");
        setLayout(null);
        setSize(400, 200);
        setLocation(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 239, 239)); // Light red background
        setResizable(false);

        // Warning label
        warningLabel = new JLabel(" you want to delete your account?", JLabel.CENTER);
        warningLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        warningLabel.setForeground(new Color(139, 0, 0)); // Dark red color
        warningLabel.setBounds(20, 20, 360, 40);
        add(warningLabel);

        // Buttons
        yesButton = new JButton("Yes");
        yesButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        yesButton.setBackground(new Color(255, 69, 58)); // Red background
        yesButton.setForeground(Color.WHITE); // White text
        yesButton.setBounds(50, 100, 80, 30);
        yesButton.addActionListener(this);
        add(yesButton);

        noButton = new JButton("No");
        noButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        noButton.setBackground(new Color(50, 205, 50)); // Green background
        noButton.setForeground(Color.WHITE);
        noButton.setBounds(160, 100, 80, 30);
        noButton.addActionListener(this);
        add(noButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        cancelButton.setBackground(new Color(70, 130, 180)); // Blue background
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(270, 100, 90, 30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == yesButton) {
            input = JOptionPane.YES_OPTION;
            performDeletion();
        } else if (ae.getSource() == noButton || ae.getSource() == cancelButton) {
            input = JOptionPane.CANCEL_OPTION;
            JOptionPane.showMessageDialog(this, "Operation Cancelled");
            dispose();
        }
    }

    private void performDeletion() {
        if (input == JOptionPane.YES_OPTION) {
            try {
                DBConnection c1 = new DBConnection();

                String q = "Delete From Admin Where Adminid ='" + AdminLogin.currentAdminID + "'";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Got some error");
                } else {
                    JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
                    dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AdminDeleteAccount();
    }
}
