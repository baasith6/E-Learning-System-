package buildathonproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import buildathonproject.Student.StudentSignup;
import buildathonproject.Teacher.TeacherSignup;

public class Signup extends JFrame implements ActionListener {
    private JPanel panel;
    private JButton studentSignUpButton, teacherSignUpButton;
    private JLabel title;

    public Signup() {
        super("Signup");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set application icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Header - Title
        title = new JLabel("Sign Up Here", JLabel.CENTER);
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

        // Student SignUp Button
        studentSignUpButton = createButton("Student Sign Up", new Color(21, 125, 213));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(studentSignUpButton, gbc);

        // Teacher SignUp Button
        teacherSignUpButton = createButton("Teacher Sign Up", new Color(21, 125, 213));
        gbc.gridy = 1;
        panel.add(teacherSignUpButton, gbc);

        // Add action listeners
        studentSignUpButton.addActionListener(this);
        teacherSignUpButton.addActionListener(this);

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
        if (ae.getSource() == studentSignUpButton) {
            setVisible(false);
            new StudentSignup();
        } else if (ae.getSource() == teacherSignUpButton) {
            setVisible(false);
            new TeacherSignup();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
