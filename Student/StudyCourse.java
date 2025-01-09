package buildathonproject.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import buildathonproject.DBConnection;
import java.sql.ResultSet;
import javax.swing.ScrollPaneConstants;

public class StudyCourse extends JFrame implements ActionListener{
    JLabel title, subjectCbLbl, courseCbLbl, courseContentLbl;
    JTextArea courseContent;
    JComboBox coursesCb;
    JPanel middlePanel;
    JScrollPane scroll;

    public StudyCourse(){
        super("Study Course");
        setLayout(new BorderLayout());

        // Title Section
        title = new JLabel("Study Course", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBackground(new Color(60, 90, 153));  // Blue background
        title.setForeground(Color.WHITE);  // White text
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Middle Panel Setup
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBackground(Color.WHITE);  // Set background to white
        add(middlePanel, BorderLayout.CENTER);

        // Course Label
        courseCbLbl = new JLabel("Select Course");
        courseCbLbl.setFont(new Font("Arial", Font.BOLD, 16));
        courseCbLbl.setBounds(80, 80, 120, 28);
        courseCbLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(courseCbLbl);

        // Course ComboBox
        coursesCb = new JComboBox(this.getCourses());
        coursesCb.setSelectedIndex(-1);  // Default to no selection
        coursesCb.setBounds(200, 80, 200, 30);  // Adjust size and position
        coursesCb.setFont(new Font("Arial", Font.PLAIN, 14));
        coursesCb.setBackground(new Color(245, 245, 245));  // Light gray background
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        // Course Content Label
        courseContentLbl = new JLabel("Course Content");
        courseContentLbl.setFont(new Font("Arial", Font.BOLD, 16));
        courseContentLbl.setBounds(80, 160, 120, 28);
        courseContentLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(courseContentLbl);

        // Course Content Area
        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        courseContent.setEditable(false);
        courseContent.setFont(new Font("Arial", Font.PLAIN, 14));
        
        scroll = new JScrollPane(courseContent);
        scroll.setBounds(80, 190, 400, 180);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));  // Border for scroll
        middlePanel.add(scroll);

        // Window settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);  // Center the window
        setResizable(false);
        setVisible(true);
    }

    private String[] getCourses(){
        String[] coursesData = null;
        try{
            DBConnection c1 = new DBConnection();
            String q = "SELECT C.Name FROM courses AS C"
                    + " INNER JOIN Enrollments AS E ON E.course_ID = C.course_ID"
                    + " WHERE E.stdID = '" + StudentLogin.currentStudentID + "'";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while(rs.next())
                rowCount++;
            coursesData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                coursesData[i] = rs.getString("Name");
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return coursesData;
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == coursesCb){
            Object selected = coursesCb.getSelectedItem();
            String courseName = selected.toString();
            try{
                DBConnection c1 = new DBConnection();
                String q1 = "SELECT Content FROM courses WHERE Name = '" + courseName + "'";
                ResultSet rs = c1.s.executeQuery(q1);
                rs.next();
                String course_Content = rs.getString("Content");
                courseContent.setText(course_Content);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new StudyCourse();
    }
}
