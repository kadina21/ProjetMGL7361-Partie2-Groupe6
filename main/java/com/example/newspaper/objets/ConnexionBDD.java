package com.example.newspaper.objets;

import java.sql.*;

public class ConnexionBDD {
	private static ConnexionBDD instance = null;
	public Connection connection = null;

	private ConnexionBDD() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dinak\\eclipse-workspace\\Projet2Conception\\test.db");
			System.out.println("DATABASE CONNECTED SUCCESSFULLY");
   
		}catch(Exception ex) {
			System.out.println("DATABASE NOT CONNECTED");
		}
	}
	
	public static ConnexionBDD getInstance() {

		if (instance == null) {
			instance = new ConnexionBDD();
		}
		
		return instance;
	}
	
	public static void main(String[] args) {
		Connection connection = ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String name = rs.getString("name");
				String firstName = rs.getString("firstName");								
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String isAuthor = rs.getString("isAuthor");
				String field = rs.getString("field");
				String job = rs.getString("job");
				System.out.println("name: " + name + ", firstName: " + firstName + ", email: " + email + ", username: " + username + ", password: " + password + ", isAuthor: " + isAuthor + "\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
