package com.example.newspaper.objets;

import com.example.newspaper.outils.ConnexionBDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Scientifique extends Lecteur implements UserAuthentication {
	private Long id;
	private String domaine;
	private String emploi;
	private boolean loggedIn;
	
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

	public void modifierProfil() {
		//sélectionne ce que veut modifier dans interface web
		//puis setter pour modifier dans la classe
		//et requête SQL
	}
	
	public void soumettreArticle() {
		
	}
	
	public String voirEtatArticle() {
		if(!isLoggedIn()) {
			authentification();
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public void creerCompte() {
		//user va entrer ses identifiants et on les rajoute à la bdd
		Connection connection= ConnexionBDD.getInstance().connection;
		Scanner sc=new Scanner(System.in, "UTF-8");

		System.out.println("Veuillez saisir votre nom.");
		String nom=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		super.nom=nom;
		System.out.println("Votre nom est "+nom);

		System.out.println("Veuillez saisir votre prénom.");
		String prenom=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		super.prenom=prenom;
		System.out.println("Votre prénom est "+prenom);

		System.out.println("Veuillez saisir votre adresse courriel.");
		String email=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		super.email=email;
		System.out.println("Votre email est "+email);

		System.out.println("Veuillez saisir un nom d'utilisateur.");
		String username=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		super.username=username;
		System.out.println("Votre nom d'utilisateur est "+username);

		System.out.println("Veuillez saisir un mot de passe.");
		String password=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		super.password=password;

		try {
			Statement s=connection.createStatement();
			/*if(estAuteur) {
				String i="INSERT INTO utilisateurs VALUES (\""+nom+"\",\""+prenom+"\",\""+email+"\",\""+username+"\",\""+password+"\",\"auteur\",NULL,NULL);\r\n";
				s.executeUpdate(i);
			}else {
				String i="INSERT INTO utilisateurs VALUES (\""+nom+"\",\""+prenom+"\",\""+email+"\",\""+username+"\",\""+password+"\",\"lecteur\",NULL,NULL);\r\n";
				s.executeUpdate(i);
			}*/
			s.close();
			/*PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1,nom);
			ps.setString(2,prenom);
			ps.setString(3,email);
			ps.setString(4,username);
			ps.setString(5,password);
			if(estAuteur) {
				ps.setString(6,"auteur");
			}else {
				ps.setString(6,"lecteur");
			}
			ps.setString(7,"NULL");
			ps.setString(8,"NULL");
			ps.execute();
			ps.close();*/
			sc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void authentification() {
		//requête SQL pour trouver username et password dans bdd
		//si identifiants introuvables une fois, message d'erreur et choix donné de créer un compte
		Connection connection=ConnexionBDD.getInstance().connection;
		Scanner sc=new Scanner(System.in, "UTF-8");
		System.out.println("Insérez votre nom d'utilisateur");
		String username = sc.nextLine();
		System.out.println("Insérez votre mot de passe");
		String password = sc.nextLine();

		//Comparer avec bdd aussi ? boolean trouve que si on utilise la bdd, sinon juste la condition ligne 129
		boolean trouve=false; //à modifier quand requête sera faite
		if (this.username==null || this.password==null) {
			trouve=false;
		}else if (username.compareTo(this.username)==0 && password.compareTo(this.password)==0) {
			trouve = true;
		}

		if(trouve) {
			setLoggedIn(true);
			System.out.println("Vous êtes connecté !");
			//quelque chose d'autre ?
		}else {
			//cas du mot de passe oublié ?
			System.out.println("Nom d'utilisateur ou mot de passe incorrect.\nRéessayer (1), mot de passe oublié (2) ou créer un compte (3) ?");
			int choix = sc.nextInt(); //A CHANGER AVEC ENTREE
			if(choix==1) {
				authentification();
			}else if (choix==2) {
				//mail avec mot de passe temporaire
				//qui sera généré auto et mis dans bdd ici ?
			}else{
				creerCompte();
			}
		}
	}
}
