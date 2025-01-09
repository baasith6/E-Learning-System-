package buildathonproject.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.*;
import buildathonproject.DBConnection;
import buildathonproject.Main;

public class StudentLogin extends JFrame implements ActionListener {
    JLabel usernameLabel, passwordLabel, iconLabel, titleLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;
    public static int currentStudentID;
    private Main mainInstance;

    public StudentLogin(Main mainInstance) {
        super("Student Login");
        this.mainInstance = mainInstance;

        // Frame properties
        setLayout(null);
        setTitle("Student Login");
        setSize(700, 400);
        setLocation(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(240, 248, 255));
        setResizable(false);

        // Title
        titleLabel = new JLabel("Student Login Portal", JLabel.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBounds(150, 20, 400, 40);
        add(titleLabel);

        // Username Label and Field
        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usernameLabel.setBounds(120, 100, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        usernameField.setBounds(250, 100, 200, 30);
        add(usernameField);

        // Password Label and Field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordLabel.setBounds(120, 150, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        passwordField.setBounds(250, 150, 200, 30);
        add(passwordField);

        // Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/second.jpg"));
        Image resizedIcon = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        iconLabel = new JLabel(new ImageIcon(resizedIcon));
        iconLabel.setBounds(500, 100, 150, 150);
        add(iconLabel);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(200, 220, 100, 40);
        loginButton.addActionListener(this);
        add(loginButton);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        cancelButton.setBackground(new Color(178, 34, 34));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBounds(350, 220, 100, 40);
        cancelButton.addActionListener(this);
        add(cancelButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            try {
                DBConnection c1 = new DBConnection();
                String u = usernameField.getText();
                String v = String.valueOf(passwordField.getPassword());
                String q = "select * from Student where username='" + u + "' and password='" + v + "'";

                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                    currentStudentID = Integer.parseInt(rs.getString("stdID"));
                    setVisible(false);
                    new Student();
                    if (mainInstance != null) {
                        mainInstance.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        new StudentLogin(null);
    }
}
