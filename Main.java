package buildathonproject;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private JPanel headerPanel, mainPanel, footerPanel;
    private JButton loginButton, signupButton;
    private JLabel title, footerLabel, mainScreenLabel, clockLabel;
    private JMenuBar menuBar;
    private JMenu fileMenu, aboutMenu;
    private JMenuItem aboutProject, aboutDevelopers, exitMenuItem;

    public Main() {
        super("E-Learning System");
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the application icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Header Panel
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(45, 45, 45));
        add(headerPanel, BorderLayout.NORTH);

        // Title with Modern Font
        title = new JLabel("Welcome to the E-Learning System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        headerPanel.add(title, BorderLayout.CENTER);

        // Clock Label
        clockLabel = new JLabel();
        clockLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        clockLabel.setForeground(Color.WHITE);
        clockLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        headerPanel.add(clockLabel, BorderLayout.SOUTH);

        // Update the clock every second
        Timer timer = new Timer(1000, e -> {
            DateFormat df = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
            Calendar cal = Calendar.getInstance();
            clockLabel.setText(df.format(cal.getTime()));
        });
        timer.start();

        // Menu Bar
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(45, 45, 45));
        menuBar.setForeground(Color.WHITE);

        fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);
        aboutMenu = new JMenu("About");
        aboutMenu.setForeground(Color.WHITE);

        aboutProject = new JMenuItem("About Project");
        aboutDevelopers = new JMenuItem("About Developers");
        exitMenuItem = new JMenuItem("Exit");

        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        fileMenu.add(exitMenuItem);
        aboutMenu.add(aboutProject);
        aboutMenu.add(aboutDevelopers);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);

        // Add Action Listeners
        exitMenuItem.addActionListener(this);
        aboutProject.addActionListener(this);
        aboutDevelopers.addActionListener(this);

        // Main Panel
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(240, 240, 240));
        add(mainPanel, BorderLayout.CENTER);

        // Login Button
        loginButton = createButton("Login", "buildathonproject/icons/login.png", 420, 50);
        signupButton = createButton("SignUp", "buildathonproject/icons/signup.png", 715, 50);

        // Add Buttons to Main Panel
        mainPanel.add(loginButton);
        mainPanel.add(signupButton);

        // Center Image
        ImageIcon mainImageIcon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/ElearningMain.png"));
        Image mainImage = mainImageIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        mainScreenLabel = new JLabel(new ImageIcon(mainImage));
        mainScreenLabel.setBounds(390, 200, 500, 500);
        mainPanel.add(mainScreenLabel);

        // Footer Panel
        footerPanel = new JPanel();
        footerPanel.setBackground(new Color(45, 45, 45));
        add(footerPanel, BorderLayout.SOUTH);

        footerLabel = new JLabel("© 2024 E-Learning System. All rights reserved.", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        footerPanel.add(footerLabel);

        setResizable(false);
        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createButton(String text, String iconPath, int x, int y) {
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image scaledIcon = icon.getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
        JButton button = new JButton(text, new ImageIcon(scaledIcon));
        button.setBounds(x, y, 210, 150);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(21, 76, 121));
        button.setFocusPainted(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover Effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(30, 100, 160));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(21, 76, 121));
            }
        });

        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            new Login();
        } else if (ae.getSource() == signupButton) {
            new Signup();
        } else if (ae.getSource() == aboutDevelopers) {
            new AboutDevelopers();
        } else if (ae.getSource() == aboutProject) {
            new AboutProject();
        } else if (ae.getSource() == exitMenuItem) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
