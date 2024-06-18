package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import database.DatabaseHelper;
import utils.TransparentButton;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class PatientForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	Color bgColor;
	Color borderColor;
	Color errorColor;

	JLabel lbPatientForm, lbName, lbAddress, lbContact, lbGender, lbBirth;
	JTextField tfName, tfAddress, tfContact;
	JComboBox<String> genderComboBox;
	JSpinner dobSpinner;
	TransparentButton submitButton;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientForm frame = new PatientForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatientForm() {

		bgColor = new Color(239, 222, 205, 130);
		borderColor = new Color(220, 220, 220, 200);
		errorColor = new Color(255, 102, 102);

		// SET UP THE FRAME AND REMOVE DEFAULT ICON
		setBackground(new Color(211, 186, 160));
		BufferedImage icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 316);

		// SET THE CONTENT PANE
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// BACKGROUND LABEL FOR THE IMAGE
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("OIP.jpg"));
		JLabel backgroundLabel = new JLabel(imageIcon);
		backgroundLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

		// CREATE LAYERED PANE TO ADD THE COMPONENTS ON TOP OF BACGROUND LABEL
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight()));
		layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

		lbPatientForm = new JLabel("PATIENT INFORMATION");
		lbPatientForm.setHorizontalAlignment(SwingConstants.CENTER);
		lbPatientForm.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 25));
		lbPatientForm.setBackground(bgColor);
		lbPatientForm.setBounds(80, 30, 300, 40);
		layeredPane.add(lbPatientForm, JLayeredPane.PALETTE_LAYER);

		lbName = new JLabel("Name");
		lbName.setHorizontalAlignment(SwingConstants.CENTER);
		lbName.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		lbName.setOpaque(true);
		lbName.setBackground(bgColor);
		lbName.setBounds(43, 92, 50, 16);
		layeredPane.add(lbName, JLayeredPane.PALETTE_LAYER);

		tfName = new JTextField();
		tfName.setHorizontalAlignment(SwingConstants.CENTER);
		tfName.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		tfName.setOpaque(true);
		tfName.setBackground(bgColor);
		tfName.setBounds(120, 91, 120, 16);
		layeredPane.add(tfName, JLayeredPane.PALETTE_LAYER);

		lbAddress = new JLabel("Address");
		lbAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lbAddress.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		lbAddress.setOpaque(true);
		lbAddress.setBackground(bgColor);
		lbAddress.setBounds(39, 132, 60, 16);
		layeredPane.add(lbAddress, JLayeredPane.PALETTE_LAYER);

		tfAddress = new JTextField();
		tfAddress.setHorizontalAlignment(SwingConstants.CENTER);
		tfAddress.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		tfAddress.setOpaque(true);
		tfAddress.setBackground(bgColor);
		tfAddress.setBounds(120, 131, 120, 16);
		layeredPane.add(tfAddress, JLayeredPane.PALETTE_LAYER);

		lbContact = new JLabel("Contact");
		lbContact.setHorizontalAlignment(SwingConstants.CENTER);
		lbContact.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		lbContact.setOpaque(true);
		lbContact.setBackground(bgColor);
		lbContact.setBounds(39, 172, 60, 16);
		layeredPane.add(lbContact, JLayeredPane.PALETTE_LAYER);

		tfContact = new JTextField();
		tfContact.setHorizontalAlignment(SwingConstants.CENTER);
		tfContact.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		tfContact.setOpaque(true);
		tfContact.setBackground(bgColor);
		tfContact.setBounds(120, 171, 120, 16);
		layeredPane.add(tfContact, JLayeredPane.PALETTE_LAYER);

		lbGender = new JLabel("Gender");
		lbGender.setHorizontalAlignment(SwingConstants.CENTER);
		lbGender.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		lbGender.setOpaque(true);
		lbGender.setBackground(new Color(239, 222, 205, 130));
		lbGender.setBounds(270, 110, 60, 16);
		layeredPane.add(lbGender, JLayeredPane.PALETTE_LAYER);

		genderComboBox = new JComboBox<>(new String[] { "Female", "Male", "Other" });
		genderComboBox.setOpaque(false);
		genderComboBox.setBackground(bgColor);
		genderComboBox.setBounds(350, 110, 75, 16);
		layeredPane.add(genderComboBox, JLayeredPane.PALETTE_LAYER);

		lbBirth = new JLabel("Birthdate");
		lbBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lbBirth.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		lbBirth.setOpaque(true);
		lbBirth.setBackground(bgColor);
		lbBirth.setBounds(268, 150, 65, 16);
		layeredPane.add(lbBirth, JLayeredPane.PALETTE_LAYER);

		dobSpinner = new JSpinner(new SpinnerDateModel());
		dobSpinner.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 14));
		dobSpinner.setOpaque(false);
		dobSpinner.setBackground(bgColor);
		dobSpinner.setBounds(350, 150, 100, 16);
		layeredPane.add(dobSpinner, JLayeredPane.PALETTE_LAYER);

		submitButton = new TransparentButton("SUBMIT", borderColor, bgColor);
		submitButton.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 19));
		submitButton.setOpaque(false);
		submitButton.setBackground(new Color(239, 222, 205, 130));
		submitButton.setForeground(Color.BLACK);
		submitButton.setBorderPainted(false);
		submitButton.setBounds(150, 220, 110, 30);
		layeredPane.add(submitButton, JLayeredPane.PALETTE_LAYER);
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				submitForm();
			}
		});

		contentPane.add(layeredPane, BorderLayout.CENTER);

	}

	private void indicateError(JComponent component, String message) {

		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(component);
		JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private boolean validateInputs() {

		if (tfName.getText().trim().isEmpty()) {
			indicateError(tfName, "Name is required.");
			return false;
		}
		if (tfAddress.getText().trim().isEmpty()) {
			indicateError(tfAddress, "Address is required.");
			return false;
		}
		if (tfContact.getText().trim().isEmpty()) {
			indicateError(tfContact, "Contact is required.");
			return false;
		}
		if (!tfContact.getText().matches("\\d{10}")) {
			indicateError(tfContact, "Contact must be a 10-digit number.");
			return false;
		}
		if (genderComboBox.getSelectedItem() == null) {
			indicateError(genderComboBox, "Gender is required.");
			return false;
		}
		try {
			new SimpleDateFormat("yyyy-MM-dd").format(dobSpinner.getValue());
		} catch (Exception e) {
			indicateError(dobSpinner, "Invalid date format.");
			return false;
		}

		return true;
	}
	


	private void submitForm() {

		String name;
		String address;
		String contact;
		String gender;
		Date dob;

		if (validateInputs()) {
			name = tfName.getText();
			address = tfAddress.getText();
			contact = tfContact.getText();
			gender = (String) genderComboBox.getSelectedItem();
			dob = (Date) dobSpinner.getValue();

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dobString = dateFormat.format(dob);
			
			DatabaseHelper.insertPatient(name, address, contact, gender, dobString);
			DatabaseHelper.retrievePatients();
		}

	}

}
