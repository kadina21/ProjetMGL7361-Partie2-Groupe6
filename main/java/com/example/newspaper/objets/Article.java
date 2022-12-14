package com.example.newspaper.objets;
//https://smeric.developpez.com/java/uml/etat/


public class Article {

	//private Scientifique auteur;
	private String id;
	private String titre;
	private EtatArticle etat;
	private Comite comite;
	private int points; 
	
	
	
	public Article() {
		
		etat = new EtatNonSoumis(this);
		points=0;
	}



	public Comite getComite() {
		return comite;
	}



	public void setComite(Comite comite) {
		this.comite = comite;
	}



	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
	
}
