package com.example.journal.article;

public class EtatNonSoumis implements EtatArticle{
    //public Article article;
    public EtatNonSoumis () {

        /*if ( article == null ) {
            System.out.println("Larticle n existe pas ! aucun article trouve");
            throw new IllegalArgumentException("L'etat d'un article est necessairement associe a l article qu'il represente");

        }*/
    }

    public String etat(){
       return "nonSoumis";
    }

    public String toString(){
        return "nonSoumis";
    }
}
