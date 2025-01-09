package buildathonproject.Teacher;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.*;

import buildathonproject.DBConnection;
import buildathonproject.Main;

public class Teacher extends JFrame implements ActionListener, WindowStateListener {
    JPanel sidePanel, rightPanel, buttonsPanel;
    JButton viewProfileBtn, logoutBtn;
    JLabel usericon, lblUsername;
    JButton b1, b2, b3, b4, b5, b6, b7;
    BufferedImage bufferedImage = null;

    public Teacher() {
        super("Teacher Module");

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
        String firstName = null, lastName = null, gender = "";
        byte[] bytImage = null;
        try {
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = c1.c.prepareStatement("SELECT * FROM Teacher WHERE teacherID = ?");
            ps.setInt(1, TeacherLogin.currentTeacherID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                gender = rs.getString("Gender");
                bytImage = rs.getBytes("picture"); // Might be null
            } else {
                JOptionPane.showMessageDialog(null, "Teacher not found in the database.");
            }
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
                usericon = new JLabel(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/defaultProfile.png")));
            }
        } else {
            usericon = new JLabel(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/defaultProfile.png")));
        }
        usericon.setBounds(52, 30, 96, 96);
        sidePanel.add(usericon);

        // Teacher Name
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
        JLabel mainTitle = new JLabel("Teacher Module");
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
                "Add Course", "View My Students", "View My Courses",
                "Update Courses"
        };

        String[] buttonIcons = {
                "ManageAccount.png", "DeleteAccount.png", "viewAccount.png",
                "addSubject.png", "viewStudents.png", "myCourses.png",
                "updateCourse.png"
        };
        
        
        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7};
        int x = 200, y = 50, width = 150, height = 100, gap = 20;

        for (int i = 0; i < buttons.length; i++) {
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
                
            }

            x += width + gap;
            if ((i + 1) % 3 == 0) { // Move to the next row after 3 buttons
                x = 200;
                y += height + gap;
            }
        }
    }

    public ImageIcon resizeImage(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        ImageIcon icon = new ImageIcon(circleBuffer);
        Image i2 = icon.getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        return i3;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            new TeacherManageAccount();
        } else if (ae.getSource() == b2) {
            TeacherDeleteAccount delete = new TeacherDeleteAccount();
            if (delete.input == 0) {
                dispose();
                new Main();
            }
        } else if (ae.getSource() == b3) {
            new TeacherAccountDetails();
        } else if (ae.getSource() == b4) {
            new AddCourse();
        } else if (ae.getSource() == b5) {
            new ViewMyStudents();
        } else if (ae.getSource() == b6) {
            new ViewMyCourses();
        } else if (ae.getSource() == b7) {
            new UpdateCourses();
        } else if (ae.getSource() == viewProfileBtn) {
            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
        } else if (ae.getSource() == logoutBtn) {
            java.util.Date dt = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String last_Login = dateFormat.format(dt);
            try {
                DBConnection c1 = new DBConnection();

                String q = "update Teacher "
                        + "Set Last_Login = '" + last_Login + "'"
                        + "Where teacherID = '" + TeacherLogin.currentTeacherID + "'";
                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Got an Error");
                } else {
                    JOptionPane.showMessageDialog(null, "Logging Out...");
                    new Main();
                    dispose();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
        // Adjust button position on window state change
        if ((e.getNewState() & Frame.NORMAL) == Frame.NORMAL) {
            logoutBtn.setLocation(30, 620);
        } else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            logoutBtn.setLocation(30, this.getHeight() - 120);
        }
    }

    public static void main(String[] args) {
        new Teacher();
    }
}
