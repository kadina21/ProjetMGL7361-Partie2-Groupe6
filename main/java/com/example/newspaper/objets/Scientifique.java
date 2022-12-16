package com.example.newspaper.objets;

import com.example.newspaper.outils.ConnexionBDD;
import com.example.newspaper.outils.GestionAPI;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;

public class Scientifique {// implements UserAuthentication {
	private int id;
	protected String nom;
	protected String prenom;
	protected String email;
	protected String username;
	protected String password;
	private String typeUser;
	private String domaine;
	private String emploi;
	private boolean loggedIn;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//Constructeur qui crée le compte de l'utilisateur
	public Scientifique(String json){
		GestionAPI gapi= GestionAPI.getInstance();
		Connection connection= ConnexionBDD.getInstance().connection;
		gapi.decodeJSONUser(json);
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id=(SELECT MAX(id) FROM utilisateurs);");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				id=rs.getInt("id");
				typeUser = rs.getString("typeUser");
				nom=rs.getString("name");
				prenom=rs.getString("firstName");
				email=rs.getString("email");
				username=rs.getString("username");
				password=rs.getString("password");
				domaine=rs.getString("field");
				emploi=rs.getString("job");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Admin.getInstance().addUser(this);
	}
	
	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public String getEmploi() {
		return emploi;
	}

	public void setEmploi(String emploi) {
		this.emploi = emploi;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void modifierProfil() {
		//sélectionne ce que veut modifier dans interface web
		//puis setter pour modifier dans la classe
		//et requête SQL
	}
	
	public void soumettreArticle() {
		
	}
	
	public String voirEtatArticle() {
		if(!isLoggedIn()) {
			//authentification();
		}
		//partie pour voir état article
		//requête SQL pour voir l'article
		return null; //return etat d'article
	}
	
	//tout est modifiable sauf nom du premier auteur
	public void modifierInfosArticle() {
		//sélectionne ce que veut modifier dans interface web
		//puis setter pour modifier dans la classe Article ?
		//et requête SQL
	}

	//@Override
	public static String authentification(String json) {
		Connection connection= ConnexionBDD.getInstance().connection;
		Object o = JSONValue.parse(json);
		JSONObject jo = (JSONObject) o;
		PreparedStatement ps;
		try{
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE username='"+jo.get("username")+"' AND password='"+jo.get("password")+"';");
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				//cas du mot de passe oublié ?
				System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
				/*int choix = sc.nextInt(); //A CHANGER AVEC ENTREE
				if(choix==1) {
					authentification(json);
				}else if (choix==2) {
					//mail avec mot de passe temporaire
					//qui sera généré auto et mis dans bdd ici ?
				}else{
					Scientifique s=new Scientifique(json);
				}*/
			}else{
				System.out.println("Vous êtes connecté !");
				for(Scientifique s : Admin.getInstance().getListeUtilisateurs()){
					if(s.getUsername()==rs.getString("username") && s.getPassword()==rs.getString("password")){
						s.setLoggedIn(true);
						break;
					}
				}
				return "{\"type_user\":\""+jo.get("type_user")+"\",\"id\":\""+rs.getString("id")+"\"}";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public String toString(){
		return nom+" "+prenom+" "+email+" "+username+" "+password+" "+typeUser+" "+domaine+" "+emploi;
	}

	public static void main(String[] args){
		String json="{\"password\":\"supermdp\",\"field\":\"Physique\",\"type_user\":\"auteur\",\"name\":\"Allo\",\"job\":\"Chercheur\",\"first_name\":\"Hello\",\"email\":\"hello2@gmail.com\",\"username\":\"alloteur2\"}";
		Scientifique s=new Scientifique(json);
		System.out.println(s.toString());
		Connection connection= ConnexionBDD.getInstance().connection;
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("SELECT * FROM utilisateurs WHERE id='"+ s.id+"';");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id"));
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
			e.printStackTrace();
		}
		String json2="{\"password\":\"supermdp3\",\"field\":\"Physique\",\"type_user\":\"auteur\",\"name\":\"Allo\",\"job\":\"Chercheur\",\"first_name\":\"Hello\",\"email\":\"hello@gmail.com\",\"username\":\"alloteur\"}";

		System.out.println(authentification(json));
		System.out.println(authentification(json2));
		for(Scientifique s2 : Admin.getInstance().getListeUtilisateurs()){
			System.out.println(s2.getUsername());
		}
	}

	public void rechercherArticle() {
		//se mettre dans la bd d'article
		//demander quel type de recherche ou bien deviner ?
		//requête SQL pour retrouver l'article
	}
}
