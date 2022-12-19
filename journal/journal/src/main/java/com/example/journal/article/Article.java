package com.example.journal.article;

import com.example.journal.evaluateur.Comite;
import com.example.journal.scientifique.Scientifique;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Where;

import java.util.List;

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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "articles_scientifiques",
    joinColumns = @JoinColumn(name = "articleId"),
    inverseJoinColumns = @JoinColumn(name = "scientifiqueId"))
    @JsonManagedReference
    private List<Scientifique> articles_scientifiques;
    private String titre;
    private String categorie;
    private String etat;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "article")
    @JsonManagedReference
    private Comite comite;
    private int points;


    public Article() {
    }

    public Article(List<Scientifique> auteurs, String titre, String categorie, EtatArticle etat, Comite comite, int points) {
        this.articles_scientifiques = auteurs;
        this.titre = titre;
        this.categorie = categorie;
        this.etat = etat.toString();
        this.comite = comite;
        this.points = points;
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
