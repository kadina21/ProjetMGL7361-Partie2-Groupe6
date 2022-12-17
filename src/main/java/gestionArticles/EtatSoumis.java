package gestionArticles;

import java.util.ArrayList;
import java.util.List;

public class EtatSoumis {

    public EtatSoumis(Article a){
        // recuperer la liste des auteurs
        List<Scientifique> auteurs = new ArrayList<>();
        auteurs= (List<Scientifique>) a.getAuteur();

        // notifier les auteur que l'article est soumis



    }
}
