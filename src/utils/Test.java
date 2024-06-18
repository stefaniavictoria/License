package utils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Test {

	private JFrame frmTest;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frmTest.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTest = new JFrame();
		frmTest.setTitle("Test");
		frmTest.setBounds(100, 100, 450, 300);
		frmTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTest.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblTest = new JLabel("                                      ");
		frmTest.getContentPane().add(lblTest);
		
		JButton btnAfisare = new JButton("Afisare");
		btnAfisare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblTest.setText("Ura");
			}
		});
		frmTest.getContentPane().add(btnAfisare);
		
		JButton btnDialog = new JButton("Dialog");
		btnDialog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Urax2");
			}
		});
		frmTest.getContentPane().add(btnDialog);
		
	}

}
