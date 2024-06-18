package forms;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import database.DatabaseHelper;
import utils.TransparentButton;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// Color bgColor = new Color(255, 255, 255, 150);
	Color bgColor = new Color(239, 222, 205, 130);
	Color borderColor = new Color(220, 220, 220, 200);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
		    Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}
	
	}
//	
//	private boolean validateInputs()
//	{
//		
//	}


	/**
	 * Create the frame.
	 */
	public LoginPage() {


		// SET UP THE FRAME AND REMOVE DEFAULT ICON
		setBackground(new Color(211, 186, 160));
		BufferedImage icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		setIconImage(icon);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);

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

		// ADD THE COMPINENTS TO THE LAYERED PANE
		// 1. THE LABEL FOR NAME
		JLabel lbName = new JLabel("Name");
		lbName.setHorizontalAlignment(SwingConstants.CENTER);
		lbName.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 13));
		lbName.setOpaque(true);
		// lbName.setBackground(new Color(255, 255, 255));
		lbName.setBackground(bgColor);
		lbName.setBounds(20, 70, 62, 20);
		layeredPane.add(lbName, JLayeredPane.PALETTE_LAYER);

		// TEXT FIELD FOR NAME
		JTextField tfName = new JTextField();
		tfName.setBounds(100, 70, 100, 20);
		tfName.setBackground(bgColor);
		layeredPane.add(tfName, JLayeredPane.PALETTE_LAYER);

		// 2. LABEL FOR PASSWORD
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lbPassword.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 13));
		lbPassword.setOpaque(true);
		// lbPassword.setBackground(new Color(255, 255, 255));
		lbPassword.setBackground(bgColor);
		lbPassword.setBounds(250, 70, 62, 20);
		layeredPane.add(lbPassword, JLayeredPane.PALETTE_LAYER);

		JTextField tfPassword = new JTextField();
		tfPassword.setBounds(330, 70, 100, 20);
		tfPassword.setBackground(bgColor);
		layeredPane.add(tfPassword, JLayeredPane.PALETTE_LAYER);

		JLabel lbWelcome = new JLabel("Welcome!");
		lbWelcome.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 36));
		lbWelcome.setOpaque(false);
		// lbWelcome.setBackground(new Color(255, 255, 255));
		lbWelcome.setBounds(20, 220, 200, 100);
		layeredPane.add(lbWelcome, JLayeredPane.PALETTE_LAYER);
		
		
		TransparentButton btnOk = new TransparentButton("LOGIN", borderColor, bgColor);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = null;
				if(tfName.getText() != null && !tfName.getText().isEmpty())
				{
					username = tfName.getText();
				}
				String password = null;
				if(tfPassword.getText() != null && !tfPassword.getText().isEmpty())
				{
					password = tfPassword.getText();
				}
				
				
				System.out.println(System.getProperty("java.class.path"));

				// Authenticate user

                boolean authenticated = DatabaseHelper.authenticateUser(username, password);

                if (authenticated) {
                    if (username.equals("admin")) {
                        openAdminForm();
                    } else {
                        openPatientForm();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Username or password is wrong!");
                }
            }
		});
		btnOk.setFont(new Font("Source Serif Pro Semibold", Font.PLAIN, 20));
		btnOk.setOpaque(false);
		btnOk.setBackground(new Color(239, 222, 205, 130));
		btnOk.setForeground(Color.BLACK);
		btnOk.setBorderPainted(false);
		btnOk.setBounds(163, 120, 100, 35);
		layeredPane.add(btnOk, JLayeredPane.PALETTE_LAYER);

		contentPane.add(layeredPane, BorderLayout.CENTER);

		//initializeDatabase();
	}


//	private boolean authenticateUser(String username, String password) {
//        Document query = new Document("username", username).append("password", password);
//        return usersCollection.find(query).first() != null;
//    }

	private void openAdminForm() {
        PatientRecords patientRecords = new PatientRecords();
        patientRecords.setVisible(true);
        dispose();
    }


	private void openPatientForm() {
		PatientForm patientForm = new PatientForm();
		patientForm.setVisible(true);
		dispose();
	}
}
