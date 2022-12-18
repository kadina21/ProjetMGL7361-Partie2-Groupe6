package com.example.journal.scientifique;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    public static Admin instance;
    private static String username="admin";
    private static String password="123456";
    private List<Scientifique> listeUtilisateurs=new ArrayList<>();
    private List<Evaluateur> listeEvaluateur=new ArrayList<>();
    private List<Comite> listeComite=new ArrayList<>();
    private List<Article> listeArticle=new ArrayList<>();

    private Admin() {}

    public static Admin getInstance() {

        if (instance == null) {
            instance = new Admin();
        }

        return instance;
    }

    public void addUser(Scientifique s){
        listeUtilisateurs.add(s);
    }

    public void addEval(Evaluateur e){
        listeEvaluateur.add(e);
    }

    public void addComite(Comite c){
        listeComite.add(c);
    }

    public void addArticle(Article a){
        listeArticle.add(a);
    }

    public List<Scientifique> getListeUtilisateurs() {
        return listeUtilisateurs;
    }

    public List<Evaluateur> getListeEvaluateur() {
        return listeEvaluateur;
    }

    public List<Comite> getListeComite() {
        return listeComite;
    }

    public List<Article> getListeArticle() {
        return listeArticle;
    }

    public Scientifique getUtilisateurByID(int id){
        for(Scientifique s : listeUtilisateurs){
            if (s.getId()==id){
                return s;
            }
        }
        return null;
    }

    public Article getArticleByID(int id){
        for(Article a : listeArticle){
            if (a.getId()==id){
                return a;
            }
        }
        return null;
    }
}
