package buildathonproject.Teacher;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.border.Border;

import buildathonproject.DBConnection;

public class AddCourse extends JFrame implements ActionListener {
    JLabel title, subjectCbLbl, courseNameLbl, courseDescriptionLbl, courseContentLbl;
    JComboBox<String> subjectsCb;
    JTextArea courseDescription, courseContent;
    JButton addBtn;
    JPanel middlePanel;
    JTextField courseName;
    String SubjectsData[];
    JScrollPane scroll, scroll2;

    public AddCourse() {
        super("Add Course");
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        // Title Header
        title = new JLabel("Add Course", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBackground(new Color(70, 130, 180)); // Steel Blue
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setPreferredSize(new Dimension(720, 50));
        add(title, BorderLayout.NORTH);

        // Middle Panel
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(new Color(240, 240, 240));
        add(middlePanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Subject Label and ComboBox
        subjectCbLbl = new JLabel("Select Subject:");
        subjectCbLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        middlePanel.add(subjectCbLbl, gbc);

        getSubjects();
        subjectsCb = new JComboBox<>(SubjectsData);
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        middlePanel.add(subjectsCb, gbc);

        // Course Name Label and TextField
        courseNameLbl = new JLabel("Course Name:");
        courseNameLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        middlePanel.add(courseNameLbl, gbc);

        courseName = new JTextField(20);
        courseName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gbc.gridx = 1;
        middlePanel.add(courseName, gbc);

        // Course Description Label and TextArea
        courseDescriptionLbl = new JLabel("Course Description:");
        courseDescriptionLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        middlePanel.add(courseDescriptionLbl, gbc);

        courseDescription = new JTextArea(5, 20);
        courseDescription.setFont(new Font("SansSerif", Font.PLAIN, 14));
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(courseDescription);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        gbc.gridx = 1;
        middlePanel.add(scroll, gbc);

        // Course Content Label and TextArea
        courseContentLbl = new JLabel("Course Content:");
        courseContentLbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        middlePanel.add(courseContentLbl, gbc);

        courseContent = new JTextArea(8, 20);
        courseContent.setFont(new Font("SansSerif", Font.PLAIN, 14));
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        scroll2 = new JScrollPane(courseContent);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        gbc.gridx = 1;
        middlePanel.add(scroll2, gbc);

        // Add Button
        addBtn = new JButton("Add Course");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(new Color(70, 130, 180)); // Steel Blue
        addBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        addBtn.addActionListener(this);
        addBtn.setToolTipText("Click to add a new course");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.add(addBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // JFrame Properties
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(720, 570);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void getSubjects() {
        try {
            DBConnection c1 = new DBConnection();
            String q = "select * from Subjects";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while (rs.next())
                rowCount++;
            SubjectsData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                SubjectsData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            String subjectStr = (String) subjectsCb.getSelectedItem();
            String courseNameStr = courseName.getText();
            String courseDescriptionStr = courseDescription.getText();
            String courseContentStr = courseContent.getText();

            try {
                DBConnection c1 = new DBConnection();
                String q1 = "Select Subject_ID From Subjects  Where Name = '" + subjectStr + "'";
                ResultSet rs = c1.s.executeQuery(q1);
                int subject_ID;
                rs.next();
                subject_ID = rs.getInt("Subject_ID");

                String q = "INSERT INTO Courses (Name, Description, Content, teacherID, Subject_ID) "
                        + "Values ('" + courseNameStr + "', '" + courseDescriptionStr + "', '" + courseContentStr + "', "
                        + TeacherLogin.currentTeacherID + ", " + subject_ID + ")";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "Some Error Occurred");
                } else {
                    JOptionPane.showMessageDialog(this, "Course Added Successfully!");
                    dispose();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "This Course is Already Offered by Another Teacher!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new AddCourse();
    }
}
