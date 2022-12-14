package com.example.newspaper.objets;

public class EtatNonSoumis implements EtatArticle{

	
	//public Article article;
	public EtatNonSoumis (Article article) {
		
		if ( article == null ) {
			System.out.println("Larticle n existe pas ! aucun article trouve");
			 throw new IllegalArgumentException("L'etat d'un article est necessairement associe a l article qu'il represente");
		//this.article =article;
		}
	}
}
