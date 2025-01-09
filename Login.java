package buildathonproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import buildathonproject.Admin.AdminLogin;
import buildathonproject.Student.StudentLogin;
import buildathonproject.Teacher.TeacherLogin;

public class Login extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton studentLoginButton, teacherLoginButton, adminLoginButton;
    private JLabel title;

    public Login() {
        super("Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set application icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Header - Title
        title = new JLabel("Login to Your Account", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(new Color(45, 76, 145));
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // Panel for buttons
        panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Student Login Button
        studentLoginButton = createButton("Student Login", new Color(21, 125, 213));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentLoginButton, gbc);

        // Teacher Login Button
        teacherLoginButton = createButton("Teacher Login", new Color(21, 125, 213));
        gbc.gridy = 1;
        panel.add(teacherLoginButton, gbc);

        // Admin Login Button
        adminLoginButton = createButton("Admin Login", new Color(21, 125, 213));
        gbc.gridy = 2;
        panel.add(adminLoginButton, gbc);

        // Add action listeners
        studentLoginButton.addActionListener(this);
        teacherLoginButton.addActionListener(this);
        adminLoginButton.addActionListener(this);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        // Footer
        JLabel footer = new JLabel("© 2024 E-Learning System", JLabel.CENTER);
        footer.setFont(new Font("Arial", Font.ITALIC, 12));
        footer.setForeground(Color.GRAY);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(footer, BorderLayout.SOUTH);

        // Frame settings
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createButton(String text, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(backgroundColor);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor);
            }
        });

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == studentLoginButton) {
            setVisible(false);
            new StudentLogin(null);
        } else if (ae.getSource() == teacherLoginButton) {
            setVisible(false);
            new TeacherLogin(null);
        } else if (ae.getSource() == adminLoginButton) {
            setVisible(false);
            new AdminLogin(null);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
