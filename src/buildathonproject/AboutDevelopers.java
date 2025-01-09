package buildathonproject;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class AboutDevelopers extends JFrame {
    JTable groupMembers;
    JPanel tablePanel;
    JLabel title;

    public AboutDevelopers() {
        super("About Developers");
        setSize(800, 600);
        setLocation(430, 280);
        setLayout(new BorderLayout());

        // Set Icon
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("buildathonproject/icons/systemIcon.png"));
        setIconImage(icon.getImage());

        // Title Section
        title = new JLabel("Meet the Team", JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setBackground(new Color(54, 57, 63));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Table Data
        String[][] rowData = {
                {"BT/HDCSE/CMU/11/01", "Abdul Baasith", "<html>Team Lead & <br> Lead Java Developer<html>"},
                {"BT/HDCSE/CMU/11/02", "Mohamed Althaf", "<html>Backend Java Developer</html>"},
                {"BT/HDCSE/CMU/11/13", "Harisha", "<html>Core Java Developer</html>"},
                {"BT/HDNET/CMU/06/02", "Ahamed Sajee", "<html>Testing and Debugging <br> Specialist</html>"},
                {"BT/HDCSE/CMU/08/03", "Janish Ahamed", "<html>Frontend Java Developer</html>"}
        };
        String columns[] = {"ICBT SID", "Name", "Contributions"};

        // Table Setup
        JTable table = new JTable(rowData, columns);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(50);
        table.setBackground(new Color(248, 248, 255));
        table.setForeground(Color.DARK_GRAY);

        // Header Customization
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));
        table.getTableHeader().setBackground(new Color(75, 101, 132));
        table.getTableHeader().setForeground(Color.WHITE);

        // Center Align Table Data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Column Resizing
        TableColumnModel colModel = table.getColumnModel();
        colModel.getColumn(0).setPreferredWidth(150);
        colModel.getColumn(1).setPreferredWidth(200);
        colModel.getColumn(2).setPreferredWidth(300);

        // Add Table to Scroll Pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        JLabel footer = new JLabel("Thank you for viewing our project!");
        footer.setFont(new Font("SansSerif", Font.ITALIC, 14));
        footer.setForeground(Color.DARK_GRAY);
        footerPanel.add(footer);
        add(footerPanel, BorderLayout.SOUTH);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AboutDevelopers();
    }
}
