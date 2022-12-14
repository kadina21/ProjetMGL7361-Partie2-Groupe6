package com.example.newspaper.objets;

import com.example.newspaper.outils.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void main(String[] args) {
		/*Lecteur l = new Lecteur();
		l.authentification();
		System.out.println(l.getNom()+l.getPrenom()+l.getUsername()+l.getPassword());*/
		
		try {
			Connection connection= ConnexionBDD.getInstance().connection;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM utilisateurs;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("firstName"));
				System.out.println(rs.getString("email"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));
				System.out.println(false);
			}
			rs.close();
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
