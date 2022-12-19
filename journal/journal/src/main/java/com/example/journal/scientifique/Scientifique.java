package com.example.journal.scientifique;

import com.example.journal.article.Article;
import com.example.journal.comite.Comite;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Scientifique {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "scientifiqueId")
    private Long id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String username;
    protected String password;
    protected String typeUser;
    protected String domaine;
    protected String emploi;
    protected boolean loggedIn;
    @ManyToOne
    @JsonManagedReference
    protected Comite comite;
    @ManyToMany(mappedBy = "articles_scientifiques",fetch = FetchType.LAZY)
    @JsonBackReference
    protected List<Article> articles;

    public Scientifique(String nom, String prenom, String email, String username, String password, String typeUser, String domaine, String emploi,List<Article> articles) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.password = password;
        this.typeUser = typeUser;
        this.domaine = domaine;
        this.emploi = emploi;
        this.articles=articles;
    }

    public Scientifique() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Comite getComite() {
        return comite;
    }

    public void setComite(Comite comite) {
        this.comite = comite;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Scientifique{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", typeUser='" + typeUser + '\'' +
                ", domaine='" + domaine + '\'' +
                ", emploi='" + emploi + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }


}
