package gestionArticles;
//https://smeric.developpez.com/java/uml/etat/


import java.util.ArrayList;
import java.util.List;

public class Article {

	private List<Scientifique> auteur;
	private String id;
	private String titre;
	//private String auteur;
	private String categorie;


	//les differents changements d'etats

	// ETAT SOUMIS :a la soumission d'un article (a appeler dans la methode soumettreArticle())
	public void appEtatSoumi(){

		this.etat= (EtatArticle) new EtatSoumis(this);

	}

	// ETAT RELECTURE : a l'assignation d'un comite d'evaluation ()
	public void appEtatRelecture(){

		if (this.points>=4){
			// si l'article a 4 points il passe en camera ready
		this.etat= (EtatArticle) new EtatRelecture();
		} else {
			//message d'erreur
		}
	}


	public Article() {

		this.etat = new EtatNonSoumis(this);
		this.points=0;
	}

	public Article(String id,String titre,  int points) { //constructeur sans id car genere avec la bdd
		this.id=id;
		this.titre = titre;

		this.points = points;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Scientifique getAuteur() {
		return (Scientifique) this.auteur;
	}

	public void setAuteur(Scientifique auteur) {
		this.auteur = (List<Scientifique>) auteur;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public EtatArticle getEtat() {
		return etat;
	}

	public void setEtat(EtatArticle etat) {
		this.etat = etat;
	}

	public Comite getComite() {
		return comite;
	}

	public void setComite(Comite comite) {
		this.comite = comite;
	}

	private EtatArticle etat;
	private Comite comite;
	private int points;

	
	
	


	public int getPoints() {
		return points;
	}



	public void setPoints(int points) {
		this.points = points;
	}
	
	
	
	
	
}
