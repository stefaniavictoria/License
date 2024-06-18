package utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TestResults extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Create the frame.
     */
    public TestResults() {
        setTitle("Test Results");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Header label
        JLabel lbHeader = new JLabel("Test Results");
        lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lbHeader.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 25));
        lbHeader.setPreferredSize(new Dimension(0, 50));
        contentPane.add(lbHeader, BorderLayout.NORTH);

        // Table to display test results
        String[] columnNames = {"Test ID", "Patient Name", "Test Name", "Date", "Result"};
        Object[][] data = {
            // Sample data, this should be populated with real test results
            {"1", "John Doe", "Blood Test", "01-01-2023", "Normal"},
            {"2", "Jane Smith", "X-Ray", "02-01-2023", "Normal"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);
        table.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Button to close the test results window
        JButton btnClose = new JButton("Close");
        btnClose.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 16));
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnClose, BorderLayout.SOUTH);
    }
}
