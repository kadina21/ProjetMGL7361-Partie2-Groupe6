package com.example.newspaper.outils;

import org.json.simple.JSONObject;

import java.util.List;

public interface APIArticle {
    public void decodeJSONArticle(String json);
    public List<JSONObject> encodeJSONArticles();
    public JSONObject encodeJSONSpecificArticle(String id);
    public List<JSONObject> encodeJSONArticlesByAuthor(String author);
    public List<JSONObject> encodeJSONArticlesByTitle(String title);
    public List<JSONObject> encodeJSONArticlesByCategorie(String categorie);
}
