package gestionArticles;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionBDD {
	private static ConnexionBDD instance = null;
	public Connection connection = null;


	public ConnexionBDD() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:test.db");
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
	
	
		
	

}
