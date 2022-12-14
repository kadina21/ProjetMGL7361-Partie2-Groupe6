package com.example.newspaper.controller;

import com.example.newspaper.outils.GestionAPI;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @GetMapping("/articles")
    public List<JSONObject> selectAllArticles(){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONArticles();
    }

    @GetMapping("/articles/{titre}")
    public List<JSONObject> selectArticlesTitre(@PathVariable(value = "titre")String titre){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONArticlesByTitle(titre);
    }

    @GetMapping("/articles_id={id_article}")
    public JSONObject selectIDArticle(@PathVariable(value = "id_article")String id) {
        GestionAPI gapi = GestionAPI.getInstance();
        return gapi.encodeJSONSpecificArticle(id);
    }

    @GetMapping("/articles/{auteur}")
    public List<JSONObject> selectArticlesAuteur(@PathVariable(value = "auteur")String auteur) {
        GestionAPI gapi = GestionAPI.getInstance();
        return gapi.encodeJSONArticlesByAuthor(auteur);
    }

    @GetMapping("/articles/{categorie}")
    public List<JSONObject> selectArticlesCategorie(@PathVariable(value = "categorie")String categorie) {
        GestionAPI gapi = GestionAPI.getInstance();
        return gapi.encodeJSONArticlesByCategorie(categorie);
    }
}
