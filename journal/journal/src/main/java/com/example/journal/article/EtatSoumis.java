package com.example.journal.article;

import com.example.journal.scientifique.Scientifique;

import java.util.ArrayList;
import java.util.List;

public class EtatSoumis implements EtatArticle{
    public EtatSoumis(Article a){
        // recuperer la liste des auteurs
        // List<Scientifique> auteurs = new ArrayList<>();
        //auteurs= (List<Scientifique>) a.getAuteur();

        // notifier les auteur que l'article est soumis
    }

    public String etat(){
        return "soumis";
    }

    public String toString(){
        return "soumis";
    }
}
