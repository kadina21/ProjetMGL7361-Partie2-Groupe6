package com.example.newspaper.controller;

import com.example.newspaper.outils.GestionAPI;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @CrossOrigin
    @GetMapping("/articles")
    public List<JSONObject> selectAllArticles(){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONArticles();
    }

    @CrossOrigin
    @GetMapping("/articles/{titre}")
    public List<JSONObject> selectArticlesTitre(@PathVariable(value = "titre")String titre){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONArticlesByTitle(titre);
    }

    @CrossOrigin
    @GetMapping("/articles_id={id_article}")
    public JSONObject selectIDArticle(@PathVariable(value = "id_article")String id) {
        GestionAPI gapi = GestionAPI.getInstance();
        return gapi.encodeJSONSpecificArticle(id);
    }

    @CrossOrigin
    @GetMapping("/articles_de_{auteur}")
    public List<JSONObject> selectArticlesAuteur(@PathVariable(value = "auteur")String auteur) {
        GestionAPI gapi = GestionAPI.getInstance();
        return gapi.encodeJSONArticlesByAuthor(auteur);
    }

    @CrossOrigin
    @GetMapping("/articles/cat={categorie}")
    public List<JSONObject> selectArticlesCategorie(@PathVariable(value = "categorie")String categorie) {
        GestionAPI gapi = GestionAPI.getInstance();
        return gapi.encodeJSONArticlesByCategorie(categorie);
    }
}
