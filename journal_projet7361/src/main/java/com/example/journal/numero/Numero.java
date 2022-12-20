package com.example.journal.numero;

import com.example.journal.article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Numero {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "numeroId")
    private Long id;
    private Date datePublication;
    @OneToMany(mappedBy = "numero")
    @JsonBackReference
    private List<Article> listArticles;

    public Numero(Date datePublication, List<Article> listArticles) {
        this.datePublication = datePublication;
        this.listArticles = listArticles;
    }

    public Numero() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public List<Article> getListArticles() {
        return listArticles;
    }

    public void setListArticles(List<Article> listArticles) {
        this.listArticles = listArticles;
    }
}
