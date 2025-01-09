package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import buildathonproject.DBConnection;

public class ViewCourses extends JFrame implements ActionListener {
    String columnNames[];
    String[][] data;
    JTable table;
    DefaultTableModel model;
    JLabel title, cbLbl;
    JComboBox filterBySubjectCb;
    JPanel middlePanel;

    public ViewCourses() {
        super("View Courses");
        setLayout(new BorderLayout());

        // Title Panel
        title = new JLabel("View Courses", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(0, 102, 204)); // Dark blue text
        title.setBackground(new Color(240, 248, 255)); // Light blue background
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Middle Panel
        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        middlePanel.setBackground(Color.WHITE); // White background for clean contrast
        add(middlePanel, BorderLayout.CENTER);

        // Filter Label
        cbLbl = new JLabel("Filter by Subject:");
        cbLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cbLbl.setBounds(50, 25, 150, 28);
        middlePanel.add(cbLbl);

        // Filter Dropdown
        filterBySubjectCb = new JComboBox(this.getSubjects());
        filterBySubjectCb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        filterBySubjectCb.setBounds(200, 25, 180, 28);
        filterBySubjectCb.addActionListener(this);
        middlePanel.add(filterBySubjectCb);

        // Courses Table
        this.getCourses(""); // Load all courses initially
        model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setGridColor(new Color(200, 200, 200));
        table.setSelectionBackground(new Color(173, 216, 230)); // Light blue for selection

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(230, 230, 230)); // Light gray header background
        header.setFont(new Font("SansSerif", Font.BOLD, 14));

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 80, 550, 300);
        middlePanel.add(pane);

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String[] getSubjects() {
        String[] subjects = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "select Name From Subjects";

            ResultSet rs = c1.s.executeQuery(q);
            int rowCount = 0;
            while (rs.next())
                rowCount++;
            subjects = new String[rowCount + 1];
            subjects[0] = "All"; // Add "All" as the default option
            int row = 1;
            rs.beforeFirst();
            while (rs.next()) {
                subjects[row] = rs.getString("Name");
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    private void getCourses(String subjectName) {
        DBConnection c1 = new DBConnection();
        try {
            String q = "select C.Name As Course, CONCAT(T.fname, ' ', T.lname) As Teacher_Name, T.Email_ID As Teacher_Email"
                    + " from Courses As C"
                    + " Inner Join Teacher As T ON T.teacherID = C.teacherID"
                    + " Where C.Subject_ID = (select Subject_ID From Subjects Where Name = '" + subjectName + "')";
            if (subjectName.equals("All") || subjectName.isEmpty()) {
                q = "select C.Name As Course, CONCAT(T.fname, ' ', T.lname) As Teacher_Name, T.Email_ID As Teacher_Email"
                        + " from Courses As C"
                        + " Inner Join Teacher As T ON T.teacherID = C.teacherID";
            }

            ResultSet rs = c1.s.executeQuery(q);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            int rowCount = 0;
            while (rs.next())
                rowCount++;
            columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = rsmd.getColumnLabel(i + 1);
            }
            data = new String[rowCount][columnCount];
            int row = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int col = 0; col < columnCount; col++) {
                    data[row][col] = rs.getString(col + 1);
                }
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c1.Close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterBySubjectCb) {
            model.setRowCount(0); // Clear all records from JTable
            model.setColumnCount(0); // Clear all columns from JTable
            String subjectName = filterBySubjectCb.getSelectedItem().toString();
            this.getCourses(subjectName);
            for (String column : columnNames) {
                model.addColumn(column);
            }
            for (String[] row : data) {
                model.addRow(row);
            }
        }
    }

    public static void main(String[] args) {
        new ViewCourses();
    }
}
