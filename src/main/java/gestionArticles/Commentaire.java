package gestionArticles;

public class Commentaire {

    public void setContenu(String nextLine) {
    }

    enum TypeComment {
		mineur,
		majeur,
		vide
	}
	
	private TypeComment type;
	private String contenu;
	
	
	public Commentaire () {
		
	}
	public Commentaire (TypeComment type, String contenu) {
		this.type=type;
		this.contenu=contenu;
	}
	public TypeComment getType() {
		return type;
	}
	public void setType(TypeComment type) {
		this.type = type;
	}
	public String getContenu() {
		return contenu;
	}
	public void redigerCommentaire(String contenu) {
		this.contenu = contenu;
	}
	
	
	
	
}
