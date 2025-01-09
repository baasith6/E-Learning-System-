package buildathonproject;

import java.awt.*;
import javax.swing.*;

public class LoadingScreen extends JFrame {
    private JPanel mainPanel;
    private JProgressBar loadingBar;
    private JLabel upperPic, textLbl, loadingLbl, percentageLbl;

    public LoadingScreen() {
        super("E-Learning System");
        setSize(1280, 720);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Set application icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Main Panel
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(30, 30, 30)); // Dark background for modern look
        add(mainPanel, BorderLayout.CENTER);

        // Logo Image
        upperPic = new JLabel();
        upperPic.setIcon(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/loadingIconImg.png")));
        upperPic.setBounds(460, 70, 360, 343);
        mainPanel.add(upperPic);

        // Welcome Text
        textLbl = new JLabel("Welcome to E-Learning System", JLabel.CENTER);
        textLbl.setFont(new Font("SansSerif", Font.BOLD, 45));
        textLbl.setForeground(Color.WHITE);
        textLbl.setBounds(240, 420, 800, 60);
        mainPanel.add(textLbl);

        // Loading Status Label
        loadingLbl = new JLabel("Loading...", JLabel.LEFT);
        loadingLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        loadingLbl.setForeground(Color.WHITE);
        loadingLbl.setBounds(20, 630, 300, 30);
        mainPanel.add(loadingLbl);

        // Percentage Label
        percentageLbl = new JLabel("0%", JLabel.RIGHT);
        percentageLbl.setFont(new Font("SansSerif", Font.PLAIN, 18));
        percentageLbl.setForeground(Color.WHITE);
        percentageLbl.setBounds(1150, 630, 100, 30);
        mainPanel.add(percentageLbl);

        // Background Image
        JLabel backgroundPic = new JLabel(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/background.png")));
        backgroundPic.setBounds(0, 0, 1280, 720);
        mainPanel.add(backgroundPic);

        // Progress Bar
        loadingBar = new JProgressBar(0, 100);
        loadingBar.setPreferredSize(new Dimension(0, 30));
        loadingBar.setForeground(new Color(50, 205, 50)); // Lime green for progress bar
        loadingBar.setBackground(Color.DARK_GRAY);
        loadingBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        loadingBar.setStringPainted(true);
        add(loadingBar, BorderLayout.SOUTH);

        // Frame settings
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Start loading simulation
        runLoading();
    }

    private void runLoading() {
        new Thread(() -> {
            try {
                for (int i = 0; i <= 100; i++) {
                    Thread.sleep(50); // Simulates loading time
                    loadingBar.setValue(i);
                    percentageLbl.setText(i + "%");

                    // Update loading messages dynamically
                    if (i < 20) {
                        loadingLbl.setText("Initializing modules...");
                    } else if (i < 40) {
                        loadingLbl.setText("Loading resources...");
                    } else if (i < 60) {
                        loadingLbl.setText("Connecting to database...");
                    } else if (i < 80) {
                        loadingLbl.setText("Setting up the environment...");
                    } else if (i < 100) {
                        loadingLbl.setText("Launching application...");
                    }

                    if (i == 100) {
                        new Main(); // Launch the main application
                        dispose(); // Close the loading screen
                    }
                }
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Loading Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start();
    }

    public static void main(String[] args) {
        new LoadingScreen();
    }
}
