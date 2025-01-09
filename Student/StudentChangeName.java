package buildathonproject.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import buildathonproject.DBConnection;

public class StudentChangeName extends JFrame implements ActionListener {
    JLabel newFNamelbl, newLNamelbl, titleLabel;
    JTextField newFName, newLName;
    JButton updateNameButton;

    public StudentChangeName() {
        // Frame properties
        setTitle("Change Name");
        setLayout(null);
        setSize(400, 300);
        setLocation(500, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255)); // Light background color
        setResizable(false);

        // Title Label
        titleLabel = new JLabel("Update Your Name", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 102, 204)); // Blue color
        titleLabel.setBounds(20, 10, 360, 30);
        add(titleLabel);

        // First Name Label
        newFNamelbl = new JLabel("First Name:");
        newFNamelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newFNamelbl.setForeground(new Color(51, 51, 51)); // Dark gray
        newFNamelbl.setBounds(30, 70, 100, 30);
        add(newFNamelbl);

        // First Name Text Field
        newFName = new JTextField();
        newFName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newFName.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Blue border
        newFName.setBounds(150, 70, 200, 40);
        add(newFName);

        // Last Name Label
        newLNamelbl = new JLabel("Last Name:");
        newLNamelbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newLNamelbl.setForeground(new Color(51, 51, 51)); // Dark gray
        newLNamelbl.setBounds(30, 130, 100, 30);
        add(newLNamelbl);

        // Last Name Text Field
        newLName = new JTextField();
        newLName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        newLName.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Blue border
        newLName.setBounds(150, 130, 200, 40);
        add(newLName);

        // Update Button
        updateNameButton = new JButton("Update");
        updateNameButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        updateNameButton.setBackground(new Color(0, 102, 204)); // Blue background
        updateNameButton.setForeground(Color.WHITE); // White text
        updateNameButton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // Padding
        updateNameButton.setBounds(125, 200, 150, 40);
        updateNameButton.addActionListener(this);
        add(updateNameButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateNameButton) {
            String FirstName = newFName.getText();
            String LastName = newLName.getText();
            try {
                DBConnection c1 = new DBConnection();

                String q = "update Student SET fname = '" + FirstName + "', lname = '" + LastName + "'"
                        + " WHERE stdID = '" + StudentLogin.currentStudentID + "'";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Got some error");
                } else {
                    JOptionPane.showMessageDialog(null, "Your Name Updated Successfully");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new StudentChangeName();
    }
}
