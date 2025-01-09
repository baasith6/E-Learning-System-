package buildathonproject.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import buildathonproject.DBConnection;

public class EnrollCourse extends JFrame implements ActionListener {
    JLabel title, subjectCbLbl, courseCbLbl, courseDescriptionLbl;
    JTextArea courseDescription;
    JComboBox<String> subjectsCb, coursesCb;
    JButton enrollBtn;
    JPanel middlePanel;
    JScrollPane scroll;

    public EnrollCourse() {
        super("Enroll Course");
        setLayout(new BorderLayout());

        // Title
        title = new JLabel("Enroll in a Course", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBackground(new Color(60, 80, 120));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Middle Panel
        middlePanel = new JPanel(new GridBagLayout());
        middlePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        middlePanel.setBackground(new Color(245, 245, 245));
        add(middlePanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Subject Label and ComboBox
        subjectCbLbl = new JLabel("Select Subject:");
        subjectCbLbl.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        middlePanel.add(subjectCbLbl, gbc);

        subjectsCb = new JComboBox<>(this.getSubjects());
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(subjectsCb, gbc);
        subjectsCb.addActionListener(this);

        // Course Label and ComboBox
        courseCbLbl = new JLabel("Select Course:");
        courseCbLbl.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        middlePanel.add(courseCbLbl, gbc);

        coursesCb = new JComboBox<>();
        coursesCb.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(coursesCb, gbc);
        coursesCb.addActionListener(this);

        // Course Description Label and TextArea
        courseDescriptionLbl = new JLabel("Course Description:");
        courseDescriptionLbl.setFont(new Font("Sans-Serif", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        middlePanel.add(courseDescriptionLbl, gbc);

        courseDescription = new JTextArea(5, 20);
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        courseDescription.setFont(new Font("Sans-Serif", Font.PLAIN, 14));
        courseDescription.setEditable(false);

        scroll = new JScrollPane(courseDescription);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        middlePanel.add(scroll, gbc);

        // Enroll Button
        enrollBtn = new JButton("Enroll");
        enrollBtn.setFont(new Font("Sans-Serif", Font.BOLD, 16));
        enrollBtn.setBackground(new Color(0, 123, 255));
        enrollBtn.setForeground(Color.WHITE);
        enrollBtn.setFocusPainted(false);
        enrollBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        enrollBtn.addActionListener(this);

        add(enrollBtn, BorderLayout.SOUTH);

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String[] getSubjects() {
        String[] subjectsData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "SELECT * FROM Subjects";
            ResultSet rs = c1.s.executeQuery(q);

            int rowCount = 0;
            while (rs.next())
                rowCount++;

            subjectsData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                subjectsData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectsData;
    }

    private void getSetCourses(int subjectID) {
        String[] coursesData;
        try {
            DBConnection c1 = new DBConnection();
            String q = "SELECT * FROM Courses WHERE Subject_ID = '" + subjectID + "'";
            ResultSet rs = c1.s.executeQuery(q);

            int rowCount = 0;
            while (rs.next())
                rowCount++;

            coursesData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                coursesData[i] = rs.getString("Name");
                i++;
            }

            for (String course : coursesData)
                coursesCb.addItem(course);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == subjectsCb) {
            coursesCb.removeAllItems();
            courseDescription.setText(null);
            Object selected = subjectsCb.getSelectedItem();
            String subjectName = selected.toString();
            try {
                DBConnection c1 = new DBConnection();
                String q1 = "SELECT Subject_ID FROM Subjects WHERE Name = '" + subjectName + "'";
                ResultSet rs = c1.s.executeQuery(q1);
                int subjectID;
                rs.next();
                subjectID = rs.getInt("Subject_ID");
                this.getSetCourses(subjectID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == coursesCb) {
            Object selectedMain = coursesCb.getSelectedItem();
            if (selectedMain == null) {
                System.out.println("Null Value");
            } else {
                Object selected = coursesCb.getSelectedItem();
                String courseName = selected.toString();
                try {
                    DBConnection c1 = new DBConnection();
                    String q1 = "SELECT Description FROM Courses WHERE Name = '" + courseName + "'";
                    ResultSet rs = c1.s.executeQuery(q1);
                    if (rs.next()) {
                        String course_Description = rs.getString("Description");
                        courseDescription.setText(course_Description);
                    } else {
                        courseDescription.setText("Course description not available.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == enrollBtn) {
            String courseNameStr = coursesCb.getSelectedItem().toString();
            java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
            try {
                DBConnection c1 = new DBConnection();
                String q1 = "SELECT course_ID FROM Courses WHERE Name = '" + courseNameStr + "'";
                ResultSet rs = c1.s.executeQuery(q1);
                int course_ID;
                rs.next();
                course_ID = rs.getInt("course_ID");

                String q = "INSERT INTO Enrollments (Enrollment_Date, course_ID, stdID) "
                        + "VALUES ('" + sqlDate + "', '" + course_ID + "', '" + StudentLogin.currentStudentID + "')";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Some Error Occurred");
                } else {
                    JOptionPane.showMessageDialog(null, "Course Enrollment Successful!");
                    dispose();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "You are already enrolled in this course!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new EnrollCourse();
    }
}
