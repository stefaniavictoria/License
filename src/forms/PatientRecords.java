package forms;

import utils.PatientRecord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class PatientRecords extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField searchPatientField;
	private JTextField searchTestField;
	private DefaultTableModel model;
	private List<PatientRecord> patientRecords = new ArrayList<>();

	private static final Color bgColor = new Color(239, 222, 205, 200);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientRecords frame = new PatientRecords();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PatientRecords() {
		// Color borderColor = new Color(220, 220, 220, 200);

		// SET UP THE FRAME AND REMOVE DEFAULT ICON
		setBackground(new Color(211, 186, 160));
		BufferedImage icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);

		// SET THE CONTENT PANE
		contentPane = new JPanel();
		// contentPane.setBackground(bgColor);
		contentPane.setLayout(new BorderLayout());
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		   contentPane.add(Box.createVerticalGlue());
		// Header label
		JLabel lbHeader = new JLabel("Patient Records");
		lbHeader.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Adjust top padding as needed
		lbHeader.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 25));
		lbHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
		lbHeader.setAlignmentY(Component.CENTER_ALIGNMENT);
//		lbHeader.setHorizontalAlignment(SwingConstants.CENTER);
//		lbHeader.setVerticalAlignment(SwingConstants.CENTER);
		contentPane.add(lbHeader);
		

		// Table to display patient records
		String[] columnNames = { "Patient Name", "Contact", "Gender", "Date of Birth", "Address", "Test Results" };
		Object[][] data = { { "John Doe", "1234567890", "Male", "01-01-1980", "123 Main St", "Blood Test: Normal" },
				{ "Jane Smith", "0987654321", "Female", "12-12-1990", "456 Elm St", "X-Ray: Normal" } };

		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));

		// table.setBounds(getBounds());
		// table.setBackground(bgColor);
		table.setRowHeight(30);
		table.setForeground(Color.BLACK);

		// Set table header background and border colors
		JTableHeader tableHeader = table.getTableHeader();
		// tableHeader.setBackground(bgColor);
		tableHeader.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 16));

		// ScrollPane for the table
		JScrollPane scrollPane = new JScrollPane(table);
		// scrollPane.setBackground(bgColor);
		contentPane.add(scrollPane);
		
		JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        searchPanel.setPreferredSize(new Dimension(0, 50));
        searchPanel.setOpaque(false); // Make the search panel transparent

        JLabel lbSearchPatient = new JLabel("Search Patient");
        searchPanel.add(lbSearchPatient);

        JTextField searchPatientField = new JTextField(15);
        searchPanel.add(searchPatientField);

        JLabel lbSearchTest = new JLabel("Search Test");
        searchPanel.add(lbSearchTest);

        JTextField searchTestField = new JTextField(15);
        searchPanel.add(searchTestField);

        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(e -> searchRecords());
        searchPanel.add(btnSearch);

        contentPane.add(searchPanel);

//		JPanel searchPanel = new JPanel();
//		searchPanel.setPreferredSize(new Dimension(0, 50));
//		searchPanel.setOpaque(false); // Make the search panel transparent
//		searchPanel.setBounds(10, 100, 100, 50); // Set bounds for the search panel
//		contentPane.add(searchPanel);
//		// searchPanel.setLayout(null);
//
//		JLabel lbSearchPatient = new JLabel("Search Patient");
//		lbSearchPatient.setBounds(10, 10, 150, 30); // Set bounds for search label
//		searchPanel.add(lbSearchPatient);
//
//		searchPatientField = new JTextField();
//		searchPatientField.setBounds(160, 10, 150, 30); // Set bounds for search field
//		searchPanel.add(searchPatientField);
//
//		JLabel lbSearchTest = new JLabel("Search Test");
//		lbSearchTest.setBounds(320, 10, 150, 30); // Set bounds for search label
//		searchPanel.add(lbSearchTest);
//
//		searchTestField = new JTextField();
//		searchTestField.setBounds(500, 10, 150, 30); // Set bounds for search field
//		searchPanel.add(searchTestField);
//
//		JButton btnSearch = new JButton("Search");
//		btnSearch.setBounds(630, 10, 100, 30); // Set bounds for search button
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				searchRecords();
//			}
//		});
//		searchPanel.add(btnSearch);

	}

	private void searchRecords() {
		String patientName = searchPatientField.getText().trim().toLowerCase();
		String testName = searchTestField.getText().trim().toLowerCase();

		model.setRowCount(0); // Clear existing rows

		for (PatientRecord record : patientRecords) {
			boolean matchesPatientName = patientName.isEmpty()
					|| record.getPatientName().toLowerCase().contains(patientName);
			boolean matchesTestName = testName.isEmpty() || record.getTestResults().toLowerCase().contains(testName);

			if (matchesPatientName && matchesTestName) {
				model.addRow(new Object[] { record.getPatientName(), record.getContact(), record.getGender(),
						record.getDateOfBirth(), record.getAddress(), record.getTestResults() });
			}
		}
	}

//    private List<PatientRecord> getSamplePatientRecords() {
//        List<PatientRecord> records = new ArrayList<>();
//        records.add(new PatientRecord("John Doe", "1234567890", "Male", "01-01-1980", "123 Main St", "Blood Test: Normal"));
//        records.add(new PatientRecord("Jane Smith", "0987654321", "Female", "12-12-1990", "456 Elm St", "X-Ray: Normal"));
//        // Add more sample data as needed
//        return records;
//    }

}
