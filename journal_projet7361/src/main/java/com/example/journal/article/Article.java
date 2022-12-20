package com.example.journal.article;

import com.example.journal.comite.Comite;
import com.example.journal.numero.Numero;
import com.example.journal.scientifique.Scientifique;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table
public class Article {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="articleId")
    private Long id;
    @Where(clause="type_user='auteur'")
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "articles_scientifiques",
    joinColumns = @JoinColumn(name = "articleId",referencedColumnName = "articleId"),
    inverseJoinColumns = @JoinColumn(name = "scientifiqueId",referencedColumnName = "scientifiqueId"))
    //@JsonManagedReference
    @JsonIgnoreProperties("auteurs")
    private List<Scientifique> articles_scientifiques;
    private String titre;
    private String categorie;
    private String etat;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "article")
    //@JsonManagedReference
    private Comite comite;
    @ManyToOne
    //@JsonManagedReference
    private Numero numero;
    private int points;
    private String contenu;


    public Article() {
    }

    public Article(List<Scientifique> auteurs, String titre, String categorie, EtatArticle etat, Comite comite, int points,String contenu) {
        this.articles_scientifiques = auteurs;
        this.titre = titre;
        this.categorie = categorie;
        this.etat = etat.toString();
        this.comite = comite;
        this.points = points;
        this.contenu=contenu;
    }

    public List<Scientifique> getArticles_scientifiques() {
        return articles_scientifiques;
    }

    public void setArticles_scientifiques(List<Scientifique> articles_scientifiques) {
        this.articles_scientifiques = articles_scientifiques;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<Scientifique> getAuteurs() {
        return articles_scientifiques;
    }

    public void setAuteurs(List<Scientifique> auteurs) {
        this.articles_scientifiques=auteurs;
    }
    public void updateAuteurs(List<Scientifique> auteurs){
        int i=1;
        for(Scientifique s : auteurs){
            this.articles_scientifiques.set(i,s);
            i++;
        }
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(EtatArticle etat) {
        this.etat = etat.toString();
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
