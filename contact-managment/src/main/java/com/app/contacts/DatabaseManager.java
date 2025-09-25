package com.app.contacts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
	private static final String URL = "jdbc:h2:./contacts";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	public DatabaseManager() {
		createTable();
	}
	
	private void createTable() {
		try (
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement()
		){
			String sql = "CREATE TABLE IF NOT EXISTS contacts ("
					+"id INT PRIMARY KEY AUTO_INCREMENT,"
					+"name VARCHAR(100),"
					+"email VARCHAR(100),"
					+"phone VARCHAR(20)"
					+ ")";
			
			stmt.execute(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addContact(Contact contact) {
		String sql = "INSERT INTO contacts(name, email, phone) VALUES(?,?,?)";
		
		try(
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = conn.prepareStatement(sql)
		){
			pstmt.setString(1,  contact.getName());
			pstmt.setString(2, contact.getEmail());
			pstmt.setString(3, contact.getPhone());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Contact> getAllContacts(){
		List<Contact> contacts = new ArrayList<>();
		String sql = "SELECT * FROM contacts";
		
		try(
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)
		){
			while(rs.next()) {
				contacts.add(new Contact(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("phone")
				));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return contacts;
		
	}
	
	public void cleanup() {
	    try (
	    	Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	        Statement stmt = conn.createStatement()
	     ) {
	        stmt.executeUpdate("DROP TABLE IF EXISTS contacts");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) {
		System.out.println("Code executed succssfully");
	}

}
