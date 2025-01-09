package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import buildathonproject.DBConnection;

public class AdminAccountDetails extends JFrame implements ActionListener {
    JPanel contentPane;
    JTextField firstname, lastname, email, username, passwordField, gender;
    JButton okButton;

    public AdminAccountDetails() {
        super("Admin Details");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null); // Center window
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE); // Light background
        setContentPane(contentPane);

        // Add border and title to panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(null);
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), 
                "Admin Details", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 18), 
                new Color(70, 130, 180)));
        detailsPanel.setBounds(30, 30, 820, 450);
        contentPane.add(detailsPanel);

        // Fetch data
        String firstName = "", lastName = "", emailId = "", userName = "", password = "", userGender = "";
        try {
            DBConnection c1 = new DBConnection();
            ResultSet rs = c1.s.executeQuery("select * from Admin where Adminid = '" + AdminLogin.currentAdminID + "'");
            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                emailId = rs.getString("Email_ID");
                userName = rs.getString("username");
                password = rs.getString("password");
                userGender = rs.getString("Gender");
            } else {
                JOptionPane.showMessageDialog(null, "Not Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Labels and fields
        addLabelAndField(detailsPanel, "First Name:", 50, 50, firstName);
        addLabelAndField(detailsPanel, "Last Name:", 50, 120, lastName);
        addLabelAndField(detailsPanel, "Email:", 50, 190, emailId);
        addLabelAndField(detailsPanel, "Username:", 450, 50, userName);
        addLabelAndField(detailsPanel, "Password:", 450, 120, password);
        addLabelAndField(detailsPanel, "Gender:", 450, 190, userGender);

        // OK Button
        okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setBackground(new Color(70, 130, 180));
        okButton.setForeground(Color.WHITE);
        okButton.setBounds(345, 510, 200, 40);
        okButton.setFocusPainted(false);
        okButton.addActionListener(this);
        contentPane.add(okButton);

        setVisible(true);
    }

    private void addLabelAndField(JPanel panel, String labelText, int x, int y, String value) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBounds(x, y, 120, 30);
        panel.add(label);

        JTextField textField = new JTextField(value);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBounds(x + 130, y, 200, 30);
        textField.setEditable(false);
        textField.setBackground(new Color(245, 245, 245));
        textField.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        panel.add(textField);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new AdminAccountDetails();
    }
}
