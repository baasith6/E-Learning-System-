package buildathonproject.Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import buildathonproject.DBConnection;

public class AddSubject extends JFrame implements ActionListener {
    private JLabel title, subjectCbLbl;
    private JButton addBtn, cancelBtn;
    private JPanel middlePanel;
    private JTextField subjectName;

    public AddSubject() {
        super("Add Subject");
        setLayout(new BorderLayout());

        // Title with new style
        title = new JLabel("Add Subject", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setBackground(new Color(0, 153, 204));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        add(title, BorderLayout.NORTH);

        // Middle Panel setup
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(new Color(245, 245, 245));
        add(middlePanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Subject Name Label
        subjectCbLbl = new JLabel("Subject Name:");
        subjectCbLbl.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        middlePanel.add(subjectCbLbl, gbc);

        // Subject Name TextField
        subjectName = new JTextField();
        subjectName.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subjectName.setToolTipText("Enter the name of the subject");
        subjectName.setPreferredSize(new Dimension(200, 28)); // Ensure proper size
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1; // Allow the text field to expand horizontally
        middlePanel.add(subjectName, gbc);


        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));

        // Add Button
        addBtn = new JButton("Add");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
        addBtn.setBackground(new Color(0, 204, 102));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.addActionListener(this);
        buttonPanel.add(addBtn);

        // Cancel Button
        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("SansSerif", Font.BOLD, 15));
        cancelBtn.setBackground(new Color(204, 0, 0));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.addActionListener(e -> dispose());
        buttonPanel.add(cancelBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            String subject_Name = subjectName.getText().trim();
            if (subject_Name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Subject Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                DBConnection c1 = new DBConnection();

                String q = "INSERT INTO Subjects (Name, Adminid) " +
                        "VALUES ('" + subject_Name + "', '" + AdminLogin.currentAdminID + "')";

                int x = c1.s.executeUpdate(q);
                if (x == 0) {
                    JOptionPane.showMessageDialog(this, "This Subject already exists!", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Subject Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while adding subject!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new AddSubject();
    }
}
