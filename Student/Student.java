package buildathonproject.Student;

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

public class Student extends JFrame implements ActionListener, WindowStateListener {
    JPanel sidePanel, rightPanel, buttonsPanel;
    JLabel usericon, lblUsername;
    JButton viewProfileBtn, logoutBtn;
    JButton b1, b2, b3, b4, b5, b6, b7, inboxBtn, sentboxBtn;
    BufferedImage bufferedImage = null;

    public Student() {
        super("Student Module");

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
            PreparedStatement ps = c1.c.prepareStatement("SELECT * FROM Student WHERE stdID = '" + StudentLogin.currentStudentID + "'");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                firstName = rs.getString("fname");
                lastName = rs.getString("lname");
                bytImage = rs.getBytes("picture");
            } else {
                JOptionPane.showMessageDialog(null, "Student not found!");
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

        // Student Name
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
        JLabel mainTitle = new JLabel("Student Module");
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
                "Enroll Course", "Study Course", "Withdraw Course",
                "View Participants", "Inbox", "Sentbox"
        };

        String[] buttonIcons = {
                "ManageAccount.png", "DeleteAccount.png", "viewAccount.png",
                "addSubject.png", "StartCourse.png", "withdrawCourse.png",
                "participants.png", "inbox.png", "sentbox.png"
        };

        JButton[] buttons = {b1, b2, b3, b4, b5, b6, b7, inboxBtn, sentboxBtn};
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
                case 7 -> inboxBtn = buttons[i];
                case 8 -> sentboxBtn = buttons[i];
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
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            new StudentManageAccount();
        }
        else if(ae.getSource() == b2){
            StudentDeleteAccount delete = new StudentDeleteAccount();
            if(delete.input == 0){
                dispose();
                new Main();
            }
        }
        else if(ae.getSource() == b3){
            new StudentAccountDetails();
        }
         else if(ae.getSource() == b4){
            new EnrollCourse();
        }
        else if(ae.getSource() == b5){
            new StudyCourse();
        }
        else if(ae.getSource() == b6){
            new WithdrawCourse();
        }
        else if(ae.getSource() == b7){
            new ViewParticipants();
        }
        else if(ae.getSource() == viewProfileBtn){
            JOptionPane.showMessageDialog(this, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
        }
        else if(ae.getSource() == logoutBtn){
            java.util.Date dt = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String last_Login = dateFormat.format(dt);
            try{
                DBConnection c1 = new DBConnection();

                String q = "update Student "
                        + "Set Last_Login = '"+ last_Login +"'"
                        + "Where stdID = '"+ StudentLogin.currentStudentID +"'";

                int x = c1.s.executeUpdate(q);
                if(x == 0){
                JOptionPane.showMessageDialog(null, "Got an Error");
                }else{
                    JOptionPane.showMessageDialog(null, "Loggin Out...");
                     new Main();
                     dispose();
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else if(ae.getSource() == inboxBtn){
            new Inbox();
        }
        else if(ae.getSource() == sentboxBtn){
            new SentBox();
        }
    }

    @Override
    public void windowStateChanged(WindowEvent e) {
          // normal state
        if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.NORMAL){
           inboxBtn.setLocation(950,10);
           sentboxBtn.setLocation(950,150);
           logoutBtn.setLocation(30, 600);
        }
        // maximized
        else if ((e.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH){
            inboxBtn.setLocation(this.getWidth()- 335, 10);
            sentboxBtn.setLocation(this.getWidth()- 335, 150);
            logoutBtn.setLocation(30, this.getHeight() - 120);
        }
    }

    public static void main(String[] args) {
        new Student();
    }
}
