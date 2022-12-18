package com.example.journal.scientifique;

/*import com.example.newspaper.objets.Article;
import com.example.newspaper.objets.Commentaire;
import com.example.newspaper.objets.Scientifique;*/

import java.util.Scanner;

public class Evaluateur extends Scientifique {

    public Evaluateur(String nom, String prenom, String email, String username, String password, String typeUser, String domaine, String emploi) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.username = username;
        this.password = password;
        this.typeUser = typeUser;
        this.domaine = domaine;
        this.emploi = emploi;
    }

    /*public void evaluer(Article A){
        Commentaire com = new Commentaire();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le contenu de votre commentaire: \n");
        //com.setContenu(scanner.nextLine());
        System.out.printf("Veuillez preciser si votre commentaire est Majeur ou Mineur ou vide: \n");
        String type = scanner.nextLine();
        if (type=="mineur")
        {
            com.setType(Commentaire.TypeComment.mineur);
        } else if (type=="majeur") {
            com.setType(Commentaire.TypeComment.majeur);
        }else if (type=="") {
            //com.setType(Commentaire.TypeComment.vide);
        }
    }*/
    //public Evaluateur(String nom, String prenom, String domaine, String emploi) {
        //super(nom, prenom, domaine, emploi);
    //}
}
