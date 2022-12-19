package com.example.journal.evaluateur;

import com.example.journal.article.Article;
import com.example.journal.scientifique.Scientifique;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table
public class Comite {
    @Id
    /*@GeneratedValue(
            strategy = GenerationType.IDENTITY
    )*/
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId")
    @JsonBackReference
    private Article article;
    @Where(clause="type_user='evaluateur'")
    @OneToMany(mappedBy = "comite")
    @JsonBackReference
    private List<Scientifique> listEval;


    public Comite() {

    }

    public Comite(String id,Article article, List<Scientifique> listEval) {
        this.id=id;
        this.article = article;
        this.listEval = listEval;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Scientifique> getListEval() {
        return listEval;
    }

    public void setListEval(List<Scientifique> listEval) {
        this.listEval = listEval;
    }
}
