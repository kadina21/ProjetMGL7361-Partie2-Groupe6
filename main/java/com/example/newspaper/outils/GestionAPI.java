package com.example.newspaper.outils;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class GestionAPI implements APIUser, APIArticle{
	public static GestionAPI instance;

	private GestionAPI() {
		// TODO Auto-generated constructor stub
	}

	public static GestionAPI getInstance() {

		if (instance == null) {
			instance = new GestionAPI();
		}

		return instance;
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
	
	public void decodeJSONUser(String jsonFile) {
		Object o = JSONValue.parse(jsonFile);
		JSONObject jo = (JSONObject) o;
		
		//Mettre dans la base de données
		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("INSERT INTO utilisateurs(typeUser,name,firstName,email,username,password,field,job) VALUES (?,?,?,?,?,?,?,?);");
			ps.setString(1,jo.get("type_user").toString());
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

	public JSONObject encodeJSONSpecificUser(String id) {
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
	public List<JSONObject> encodeJSONUsers() {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT id FROM utilisateurs;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecificUser(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}

	public List<JSONObject> encodeJSONTypeUser(String typeUser) {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE typeUser='"+typeUser+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecificUser(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}
	
	public static void main(String[] args) {
		/*JSONObject jo=encodeJSONSpecificUser("1");
		System.out.println(jo);
		String s = "{\"typeUser\":\"evaluateur\",\"password\":\"mdpcplx\",\"field\":\"Physique\",\"name\":\"Hubert\",\"id\":\"1\",\"job\":\"Consultant\",\"first_name\":\"Uber\",\"email\":\"uber@gmail.com\",\"username\":\"nottheapp\"}";
		//decodeJSONUser(s);
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
		}*/
	}

	@Override
	public void decodeJSONArticle(String json) {
		Object o = JSONValue.parse(json);
		JSONObject jo = (JSONObject) o;

		//Mettre dans la base de données
		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("INSERT INTO articles(titre,auteur,categorie,comite,etat,points) VALUES (?,?,?,?,?,?);");
			ps.setString(1,jo.get("titre").toString());
			ps.setString(2,jo.get("auteur").toString());
			ps.setString(3,jo.get("categorie").toString());
			ps.setString(4,jo.get("comite").toString());
			ps.setString(5,jo.get("etat").toString());
			ps.setString(6,jo.get("points").toString());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<JSONObject> encodeJSONArticles() {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT id FROM articles;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecificArticle(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}

	public JSONObject encodeJSONSpecificArticle(String id) {
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM articles WHERE id='"+id+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				jo.put("id", rs.getString("id"));
				jo.put("titre", rs.getString("titre"));
				jo.put("auteur", rs.getString("auteur"));
				jo.put("categorie", rs.getString("categorie"));
				jo.put("comite", rs.getString("comite"));
				jo.put("etat", rs.getString("etat"));
				jo.put("points", rs.getString("points"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jo;
	}

	@Override
	public List<JSONObject> encodeJSONArticlesByAuthor(String author) {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM articles WHERE auteur='"+author+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecificArticle(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}

	@Override
	public List<JSONObject> encodeJSONArticlesByTitle(String title) {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM articles WHERE titre='"+title+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecificArticle(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}

	@Override
	public List<JSONObject> encodeJSONArticlesByCategorie(String categorie) {
		List<JSONObject> listJSON = new ArrayList<>();
		JSONObject jo = new JSONObject();

		Connection connection=ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM articles WHERE categorie='"+categorie+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				listJSON.add(encodeJSONSpecificArticle(rs.getString("id")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//HEADER CONTENT-TYPE APPLICATION/JSON
		return listJSON;
	}
}
