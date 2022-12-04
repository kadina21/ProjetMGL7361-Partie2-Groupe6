import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Lecteur implements UserAuthentication {
	private String nom;
	private String prenom;
	private String email;
	private String username;
	private String password;
	private boolean loggedIn;
	private boolean auteur;
	
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

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isAuteur() {
		return auteur;
	}

	public void setAuteur(boolean auteur) {
		this.auteur = auteur;
	}
	
	public void creerCompte(boolean estAuteur) {
		//user va entrer ses identifiants et on les rajoute à la bdd
		Connection connection=ConnexionBDD.getInstance().connection;
		Scanner sc=new Scanner(System.in, "UTF-8");
		
		System.out.println("Veuillez saisir votre adresse courriel.");
		String email=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		this.email=email;
		System.out.println("votre email est "+email);
		
		System.out.println("Veuillez saisir un nom d'utilisateur.");
		String username=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		this.username=username;
		System.out.println("votre nom d'utilisateur est "+this.username);
		
		System.out.println("Veuillez saisir un mot de passe.");
		String password=sc.nextLine(); //A CHANGER AVEC ENTREE WEB
		this.password=password;
		
		try {
			Statement s=connection.createStatement();
			if(estAuteur) {
				String i="INSERT INTO utilisateurs VALUES (\""+nom+"\",\""+prenom+"\",\""+email+"\",\""+username+"\",\""+password+"\",\"auteur\",NULL,NULL);\r\n";
				s.executeUpdate(i);
			}else {
				String i="INSERT INTO utilisateurs VALUES (\""+nom+"\",\""+prenom+"\",\""+email+"\",\""+username+"\",\""+password+"\",\"lecteur\",NULL,NULL);\r\n";
				s.executeUpdate(i);
			}
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
			}else {
				creerCompte(auteur);
			}
		}		
	}
	
	public void rechercherArticle() {
		//se mettre dans la bd d'article
		//demander quel type de recherche ou bien deviner ?
		//requête SQL pour retrouver l'article
	}
	
	public static void main(String[] args) {
		Lecteur l = new Lecteur();
		try {
			Connection connection=ConnexionBDD.getInstance().connection;
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM utilisateurs;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				l.setNom(rs.getString("name"));
				l.setPrenom(rs.getString("firstName"));
				l.setEmail(rs.getString("email"));
				l.setUsername(rs.getString("username"));
				l.setPassword(rs.getString("password"));
				l.setAuteur(false);
			}
			rs.close();
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.authentification();
		System.out.println(l.getNom()+l.getPrenom()+l.getUsername()+l.getPassword());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
