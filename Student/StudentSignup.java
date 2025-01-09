package buildathonproject.Student;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import buildathonproject.DBConnection;

public class StudentSignup extends JFrame implements ActionListener, FocusListener {
    private JPanel contentPane;
    private JTextField firstname, lastname, email, username;
    private JPasswordField passwordField;
    private JButton registerButton, uploadPicBtn;
    private JRadioButton maleRB, femaleRB;
    private ButtonGroup radioBtns;
    private JLabel fnameValidation, lnameValidation, emailValidation, userNameValidation, profilePicLbl;
    private FileInputStream fis = null;
    private File f = null;

    public StudentSignup() {
        super("Student SignUp");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1014, 515);
        setLocation(210, 110);
        setResizable(false);

        // Main Panel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(240, 248, 255)); // Light background
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title Label
        JLabel titleLabel = new JLabel("Student Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // Dark Blue
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(300, 10, 400, 40);
        contentPane.add(titleLabel);

        // Profile Picture
        profilePicLbl = new JLabel();
        profilePicLbl.setIcon(new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/uploadPicIcon.png")));
        profilePicLbl.setBounds(450, 60, 96, 96);
        contentPane.add(profilePicLbl);

        uploadPicBtn = new JButton("Upload");
        uploadPicBtn.setBounds(460, 170, 75, 23);
        uploadPicBtn.setBackground(new Color(30, 144, 255)); // Dodger Blue
        uploadPicBtn.setForeground(Color.WHITE);
        uploadPicBtn.addActionListener(this);
        contentPane.add(uploadPicBtn);

        // First Name
        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblFirstName.setBounds(100, 200, 150, 30);
        contentPane.add(lblFirstName);

        firstname = new JTextField();
        firstname.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        firstname.setBounds(230, 200, 220, 30);
        firstname.addFocusListener(this);
        contentPane.add(firstname);

        fnameValidation = new JLabel();
        fnameValidation.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        fnameValidation.setForeground(Color.RED);
        fnameValidation.setBounds(230, 230, 200, 20);
        contentPane.add(fnameValidation);

        // Last Name
        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblLastName.setBounds(100, 250, 150, 30);
        contentPane.add(lblLastName);

        lastname = new JTextField();
        lastname.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lastname.setBounds(230, 250, 220, 30);
        lastname.addFocusListener(this);
        contentPane.add(lastname);

        lnameValidation = new JLabel();
        lnameValidation.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lnameValidation.setForeground(Color.RED);
        lnameValidation.setBounds(230, 280, 200, 20);
        contentPane.add(lnameValidation);

        // Email
        JLabel lblEmail = new JLabel("Email Address:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEmail.setBounds(100, 300, 150, 30);
        contentPane.add(lblEmail);

        email = new JTextField();
        email.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        email.setBounds(230, 300, 220, 30);
        email.addFocusListener(this);
        contentPane.add(email);

        emailValidation = new JLabel();
        emailValidation.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        emailValidation.setForeground(Color.RED);
        emailValidation.setBounds(230, 330, 200, 20);
        contentPane.add(emailValidation);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblUsername.setBounds(550, 200, 150, 30);
        contentPane.add(lblUsername);

        username = new JTextField();
        username.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        username.setBounds(680, 200, 220, 30);
        username.addFocusListener(this);
        contentPane.add(username);

        userNameValidation = new JLabel();
        userNameValidation.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        userNameValidation.setForeground(Color.RED);
        userNameValidation.setBounds(680, 230, 200, 20);
        contentPane.add(userNameValidation);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPassword.setBounds(550, 250, 150, 30);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordField.setBounds(680, 250, 220, 30);
        contentPane.add(passwordField);

        // Gender
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGender.setBounds(550, 300, 150, 30);
        contentPane.add(lblGender);

        maleRB = new JRadioButton("Male");
        femaleRB = new JRadioButton("Female");
        maleRB.setActionCommand("Male");
        femaleRB.setActionCommand("Female");
        maleRB.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        femaleRB.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        maleRB.setBounds(680, 300, 80, 30);
        femaleRB.setBounds(760, 300, 100, 30);
        contentPane.add(maleRB);
        contentPane.add(femaleRB);

        radioBtns = new ButtonGroup();
        radioBtns.add(maleRB);
        radioBtns.add(femaleRB);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        registerButton.setBounds(400, 400, 200, 50);
        registerButton.setBackground(new Color(30, 144, 255)); // Dodger Blue
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(this);
        contentPane.add(registerButton);

        setVisible(true);
    }

    public ImageIcon resizeImage(String imagePath){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
        } catch (IOException ex) {
            Logger.getLogger(StudentSignup.class.getName()).log(Level.SEVERE, null, ex);
        }
        int width = bufferedImage.getWidth();
        BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleBuffer.createGraphics();
        g2.setClip(new Ellipse2D.Float(0, 0, width, width));
        g2.drawImage(bufferedImage, 0, 0, width, width, null);
        ImageIcon icon = new ImageIcon(circleBuffer);
        Image i2 = icon.getImage().getScaledInstance(96 ,96 ,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        return i3;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == uploadPicBtn){
            String fname = null;
            JFileChooser fchoser=new JFileChooser();
            fchoser.showOpenDialog(null);
            f = fchoser.getSelectedFile();
            fname = f.getAbsolutePath();
            ImageIcon micon=new ImageIcon(fname);
            
            try {
                File image=new File(fname);
                fis = new FileInputStream(image);
                profilePicLbl.setIcon(resizeImage(fname));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else{
            String firstName = firstname.getText();
            String lastName = lastname.getText();
            String emailId = email.getText();
            String userName = username.getText();
            String password = String.valueOf(passwordField.getPassword());
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            String msg = "" + firstName;
            String genderStr = "";
            if(radioBtns.getSelection() != null)
                genderStr = radioBtns.getSelection().getActionCommand();

            if(fnameValidation.getText().isEmpty() && 
            lnameValidation.getText().isEmpty() && 
            emailValidation.getText().isEmpty() && 
            userNameValidation.getText().isEmpty()){
                if(firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || userName.isEmpty() || password.isEmpty()
                    || genderStr.isEmpty() || this.f == null || this.fis == null){
                JOptionPane.showMessageDialog(null, "Please Fill All Fields !");
                }
                else{
                    try{
                        DBConnection c1 = new DBConnection();

                        PreparedStatement ps = c1.c.prepareStatement("Insert into Student (fname, lname, Email_ID, username, password, Registration_Date, Gender, picture) "
                                + "values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                        ps.setString(3, emailId);
                        ps.setString(4, userName);
                        ps.setString(5, password);
                        ps.setDate(6, sqlDate);
                        ps.setString(7, genderStr);
                        ps.setBinaryStream(8,(InputStream)fis,(int)f.length());
                        int x =  ps.executeUpdate();
                        if(x == 0){
                            JOptionPane.showMessageDialog(null, "This is User already exist");
                        }else{
                            JOptionPane.showMessageDialog(null, "Welcome, "+ msg + " Your account is successfully created."
                                    + "You can Now Log into your Account.");
                            setVisible(false);
                            new StudentLogin(null);
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Please Fill accurate Info !");
            }
        }
    }
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == firstname)
            fnameValidation.setText("");
        else if(e.getSource() == lastname)
            lnameValidation.setText("");
        else if(e.getSource() == email)
            emailValidation.setText("");
        else if(e.getSource() == username)
            userNameValidation.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == firstname){
            String fName = firstname.getText();
            if(fName.isEmpty()){
                fnameValidation.setText("Enter First Name");
            }
            else{
                boolean valid = fName.matches("[A-Z][a-z]*");
                if(!valid)
                    fnameValidation.setText("Invalid First Name");
                else
                    fnameValidation.setText("");
            }
        }
        else if(e.getSource() == lastname){
            String LName = lastname.getText();
            if(LName.isEmpty()){
                lnameValidation.setText("Enter Last Name");
            }
            else{
                boolean valid = LName.matches("[A-Z][a-z]*");
                if(!valid)
                    lnameValidation.setText("Invalid Last Name");
                else
                    lnameValidation.setText("");
            }
        }
        else if(e.getSource() == email){
            String emailTxt = email.getText();
            if(emailTxt.isEmpty()){
                emailValidation.setText("Enter Email");
            }
            else{
                boolean valid = emailTxt.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}");
                if(!valid)
                    emailValidation.setText("Invalid Email");
                else
                    emailValidation.setText("");
            }
        }
        else if(e.getSource() == username){
            String usernameTxt = username.getText();
            if(usernameTxt.isEmpty()){
                userNameValidation.setText("Enter UserName");
            }
            else{
                boolean valid = usernameTxt.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b");
                if(!valid)
                    userNameValidation.setText("Invalid UserName");
                else
                    userNameValidation.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new StudentSignup();
    }
}
