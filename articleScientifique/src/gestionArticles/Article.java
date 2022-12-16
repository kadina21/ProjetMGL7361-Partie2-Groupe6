package gestionArticles;
//https://smeric.developpez.com/java/uml/etat/


public class Article {

	//private Scientifique auteur;
	private String id;
	private String titre;
	private EtatArticle etat;
	//private Comite comite;
	private int points; 
	
	
	
	public Article() {
		
		etat = new EtatNonSoumis(this);
		points=0;
	}






	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
	
}
