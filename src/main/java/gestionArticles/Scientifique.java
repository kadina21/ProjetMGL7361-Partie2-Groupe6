package gestionArticles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Scientifique extends Lecteur {
	private String domaine;
	private String emploi;

	public Scientifique(String nom, String prenom, String domaine, String emploi) {
		super();
	}

	public void setAuteur() {
		super.setAuteur(true);
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
	
	public void creerCompte() {
		super.creerCompte(true);
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
}
