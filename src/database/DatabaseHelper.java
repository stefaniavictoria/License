package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {

	 public static final String usersURL = "jdbc:sqlite:C:\\Users\\user\\eclipse-workspace\\License\\users.db";
	 public static final String patientsURL = "jdbc:sqlite:C:\\Users\\user\\eclipse-workspace\\License\\patientInformation.db";


	    public static Connection connect(String URL) {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(URL);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }

	    public static boolean authenticateUser(String username, String password) {
	        String sql = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";
	        try (Connection conn = connect(usersURL);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, username);
	            pstmt.setString(2, password);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next() && rs.getInt(1) == 1) {
	                return true;
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }
	    
	    public static void insertPatient(String name, String address, String contact, String gender, String dob) {
	        String sql = "INSERT INTO patientData(Name, Address, Contact, Gender, Birthdate) VALUES(?,?,?,?,?)";

	        try (Connection conn = connect(patientsURL);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, name);
	            pstmt.setString(2, address);
	            pstmt.setString(3, contact);
	            pstmt.setString(4, gender);
	            pstmt.setString(5, dob);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    
	    public static void retrievePatients() {
	        String sql = "SELECT Name, Address, Contact, Gender, Birthdate FROM patientData";

	        try (Connection conn = connect(patientsURL);
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {

	            // Loop through the result set and print each record
	            while (rs.next()) {
	                System.out.println("Name: " + rs.getString("Name"));
	                System.out.println("Address: " + rs.getString("Address"));
	                System.out.println("Contact: " + rs.getString("Contact"));
	                System.out.println("Gender: " + rs.getString("Gender"));
	                System.out.println("Date of Birth: " + rs.getString("Birthdate"));
	                System.out.println("-------------");
	            }
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
}
