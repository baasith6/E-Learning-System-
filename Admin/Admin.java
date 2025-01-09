package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import buildathonproject.DBConnection;
import buildathonproject.Main;

public class Admin extends JFrame implements ActionListener, WindowStateListener {
    JPanel sidePanel, rightPanel, buttonsPanel;
    JButton manageAccount, viewProfileBtn, logoutBtn;
    JLabel usericon, lblUsername;
    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9;
    BufferedImage bufferedImage = null;

    public Admin() {
        super("Admin Module");

        // Frame setup
        setSize(1280, 720);
        setLocation(35, 30);
        setLayout(new BorderLayout());
        setResizable(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Custom Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Side Panel
        sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(41, 54, 70)); // Dark background for modern look
        sidePanel.setPreferredSize(new Dimension(200, 720));
        add(sidePanel, BorderLayout.WEST);

        // Profile Details
        String firstName = null, lastName = null;
        byte[] bytImage = null;
        try {
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = c1.c.prepareStatement("SELECT * FROM Admin WHERE Adminid = '" + AdminLogin.currentAdminID + "'");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                bytImage = rs.getBytes("picture");
            } else {
                JOptionPane.showMessageDialog(null, "Admin not found!");
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // User Icon
        if (bytImage != null) {
            try {
                InputStream is = new ByteArrayInputStream(bytImage);
                bufferedImage = ImageIO.read(is);
                usericon = new JLabel(resizeImage(bufferedImage));
            } catch (Exception ex) {
                usericon = new JLabel(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/uploadPicIcon.png")));
            }
        } else {
            usericon = new JLabel(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/uploadPicIcon.png")));
        }
        usericon.setBounds(52, 30, 96, 96);
        sidePanel.add(usericon);

        // Admin Name
        lblUsername = new JLabel(firstName + " " + lastName);
        lblUsername.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(30, 140, 140, 30);
        lblUsername.setHorizontalAlignment(JLabel.CENTER);
        sidePanel.add(lblUsername);

        // View Profile Button
        viewProfileBtn = createButton("View Profile", 30, 200, 140, 30);
        sidePanel.add(viewProfileBtn);

        // Logout Button
        logoutBtn = createButton("Logout", 30, 620, 140, 30);
        sidePanel.add(logoutBtn);

        // Right Panel
        rightPanel = new JPanel(new BorderLayout());
        add(rightPanel, BorderLayout.CENTER);

        // Title
        JLabel mainTitle = new JLabel("Admin Module");
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setFont(new Font("Verdana", Font.BOLD, 40));
        mainTitle.setBackground(new Color(0, 123, 255)); // Blue header
        mainTitle.setForeground(Color.WHITE);
        mainTitle.setOpaque(true);
        mainTitle.setPreferredSize(new Dimension(0, 80));
        rightPanel.add(mainTitle, BorderLayout.NORTH);

        // Buttons Panel
        buttonsPanel = new JPanel(null);
        buttonsPanel.setBackground(Color.WHITE);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Buttons
        createAndAddButtons();
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(new Color(23, 162, 184)); // Light blue buttons
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(this);
        button.setFocusable(false);
        return button;
    }

    private void createAndAddButtons() {
        String[] buttonLabels = {
                "Manage Account", "Delete Account", "View Account",
                "Add Subject", "Delete Subject", "View Students",
                "View Teachers", "View Courses", "Add New Admin"
        };

        String[] buttonIcons = {
                "ManageAccount.png", "DeleteAccount.png", "viewAccount.png",
                "addSubject.png", "deleteSubject.png", "viewStudents.png",
                "viewTeachers.png", "viewCourses.png", "addNewAdmin.png"
        };

        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
        int x = 200, y = 50, width = 150, height = 100, gap = 20;

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setIcon(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/" + buttonIcons[i])));
            buttons[i].setBounds(x, y, width, height);
            buttons[i].setHorizontalTextPosition(JButton.CENTER);
            buttons[i].setVerticalTextPosition(JButton.BOTTOM);
            buttons[i].addActionListener(this);
            buttonsPanel.add(buttons[i]);
            
            // Assign to the class-level variable
            switch (i) {
                case 0 -> b1 = buttons[i];
                case 1 -> b2 = buttons[i];
                case 2 -> b3 = buttons[i];
                case 3 -> b4 = buttons[i];
                case 4 -> b5 = buttons[i];
                case 5 -> b6 = buttons[i];
                case 6 -> b7 = buttons[i];
                case 7 -> b8 = buttons[i];
                case 8 -> b9 = buttons[i];
            }

            x += width + gap;
            if (x + width > getWidth() - 200) {
                x = 200;
                y += height + gap;
            }
        }
    }

    private ImageIcon resizeImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        Image i2 = new ImageIcon(circleBuffer).getImage().getScaledInstance(96, 96, Image.SCALE_SMOOTH);
        return new ImageIcon(i2);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // No change to functional logic
        if (ae.getSource() == b1) {
            new AdminManageAccount();
        } else if (ae.getSource() == b2) {
            AdminDeleteAccount delete = new AdminDeleteAccount();
            if (delete.input == 0) {
                dispose();
                new Main();
            }
        } else if (ae.getSource() == b4) {
            new AddSubject();
        } else if (ae.getSource() == b5) {
            new DeleteSubject();
        } else if (ae.getSource() == b3) {
            new AdminAccountDetails();
        } else if (ae.getSource() == viewProfileBtn) {
            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
        } else if (ae.getSource() == logoutBtn) {
            JOptionPane.showMessageDialog(null, "Logging Out...");
            new Main();
            dispose();
        } else if (ae.getSource() == b6) {
            new ViewStudents();
        } else if (ae.getSource() == b7) {
            new ViewTeachers();
        } else if (ae.getSource() == b8) {
            new ViewCourses();
        } else if (ae.getSource() == b9) {
            new AdminSignup();
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL) {
            logoutBtn.setLocation(30, 600);
        } else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            logoutBtn.setLocation(30, this.getHeight() - 120);
        }
    }

    public static void main(String[] args) {
        new Admin();
    }
}
