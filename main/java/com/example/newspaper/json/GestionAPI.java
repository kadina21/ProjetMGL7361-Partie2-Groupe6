package com.example.newspaper.json;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.newspaper.objets.ConnexionBDD;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

public class GestionAPI {

	public GestionAPI() {
		// TODO Auto-generated constructor stub
	}
	
	/*public static void ordonnerJSON(String jsonFile) {
		JSONParser parser = new JSONParser();
	    try {
	        JSONObject o = (JSONObject) parser.parse(jsonFile);
	        JSONArray array = (JSONArray) o.get("results");
	        ArrayList<JSONObject> list = new ArrayList<>();

	        for (int i = 0; i < array.size(); i++) {
	            list.add((JSONObject) array.get(i));
	        }
	        Collections.sort(list, new ComparateurJSON());

	        for (JSONObject obj : list) {
	            System.out.println(((JSONObject) obj.get("attributes")).get("OBJECTID"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}*/
	
	public static void decodeJSON(String jsonFile) {
		Object o = JSONValue.parse(jsonFile);
		JSONObject jo = (JSONObject) o;
		
		//Mettre dans la base de données
		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("INSERT INTO utilisateurs(typeUser,name,firstName,email,username,password,field,job) VALUES (?,?,?,?,?,?,?,?);");
			ps.setString(1,jo.get("typeUser").toString());
			ps.setString(2,jo.get("name").toString());
			ps.setString(3,jo.get("first_name").toString());
			ps.setString(4,jo.get("email").toString());
			ps.setString(5,jo.get("username").toString());
			ps.setString(6,jo.get("password").toString());
			ps.setString(7,jo.get("field").toString());
			ps.setString(8,jo.get("job").toString());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//faire version sans paramètre pour avoir tous les utilisateurs !!
	public static JSONObject encodeJSONSpecific(String id) {
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id='"+id+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				jo.put("id", rs.getString("id"));
				jo.put("type_user", rs.getString("typeUser"));
				jo.put("name", rs.getString("name"));
				jo.put("first_name", rs.getString("firstName"));
				jo.put("email", rs.getString("email"));
				jo.put("username", rs.getString("username"));
				jo.put("password", rs.getString("password"));
				jo.put("field", rs.getString("field"));
				jo.put("job", rs.getString("job"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return jo;
		
	}
	public static List<JSONObject> encodeJSONUsers() {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT id FROM utilisateurs;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecific(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}

	public static List<JSONObject> encodeJSONTypeUser(String typeUser) {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE typeUser='"+typeUser+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecific(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}
	
	public static void main(String[] args) {
		JSONObject jo=encodeJSONSpecific("1");
		System.out.println(jo);
		String s = "{\"typeUser\":\"evaluateur\",\"password\":\"mdpcplx\",\"field\":\"Physique\",\"name\":\"Hubert\",\"id\":\"1\",\"job\":\"Consultant\",\"first_name\":\"Uber\",\"email\":\"uber@gmail.com\",\"username\":\"nottheapp\"}";
		decodeJSON(s);
		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id='"+3+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				System.out.println(rs.getString("typeUser"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("firstName"));
				System.out.println(rs.getString("email"));
				System.out.println(rs.getString("username"));
				System.out.println(rs.getString("password"));
				System.out.println(rs.getString("field"));
				System.out.println(rs.getString("job"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
