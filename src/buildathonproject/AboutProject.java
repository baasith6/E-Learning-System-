package buildathonproject;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class AboutProject extends JFrame {
    JTextArea aboutText;
    JLabel title;

    public AboutProject() {
        super("About Project");
        setSize(600, 400);
        setLocation(430, 280);
        setLayout(new BorderLayout());

        // Set Application Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Title Section with Gradient Background
        title = new JLabel("E-Learning System", JLabel.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(66, 135, 245), getWidth(), 0, new Color(98, 173, 255));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setOpaque(false); // Background handled in custom painting
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Main Content
        String txt = "→  E-Learning System is a desktop-based Windows application developed in Java with Swing and AWT. "
                + "This project aims to serve students and teachers in online-based learning.\n\n"
                + "→  Admins can add subjects, teachers can add courses to a particular subject, "
                + "and students can enroll in courses to study them. Students can also message other participants "
                + "in a course (excluding the teacher).";

        aboutText = new JTextArea(txt);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setFont(new Font("Arial", Font.PLAIN, 18));
        aboutText.setForeground(new Color(50, 50, 50));
        aboutText.setBackground(new Color(245, 245, 245));
        aboutText.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(66, 135, 245), 2, true),
                new EmptyBorder(10, 15, 10, 15)
        ));
        aboutText.setEditable(false);
        add(aboutText, BorderLayout.CENTER);

        // Footer Section
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(230, 230, 230));
        JLabel footerLabel = new JLabel("Developed with ♥ by Team Transit Codex");
        footerLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerPanel.add(footerLabel);
        add(footerPanel, BorderLayout.SOUTH);

        // Frame Properties
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AboutProject();
    }
}
