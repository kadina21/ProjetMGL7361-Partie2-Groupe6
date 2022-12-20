package com.example.journal.article;

public class EtatAccepte implements EtatArticle{
    public EtatAccepte(){
        //notifier les utilisateur avec les consigne
        //System.out.println("l'auteur de l'article va etre notifie");
    }

    public String etat(){
        return "accepte";
    }

    public String toString(){
        return "accepte";
    }
}
