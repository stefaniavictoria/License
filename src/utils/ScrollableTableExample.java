package utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScrollableTableExample {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Scrollable Table Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        // Define column names
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        
        // Sample data
        Object[][] data = {
            {"Row1-Col1", "Row1-Col2", "Row1-Col3"},
            {"Row2-Col1", "Row2-Col2", "Row2-Col3"},
            {"Row3-Col1", "Row3-Col2", "Row3-Col3"},
            {"Row4-Col1", "Row4-Col2", "Row4-Col3"},
            {"Row5-Col1", "Row5-Col2", "Row5-Col3"}
        };
        
        // Create the table model
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        
        // Create the table with the model
        JTable table = new JTable(tableModel);
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add the scroll pane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
