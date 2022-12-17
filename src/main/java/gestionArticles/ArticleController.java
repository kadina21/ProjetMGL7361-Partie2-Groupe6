package gestionArticles;
import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@RequestMapping(path = "api/v1/student")
public class ArticleController {

    public static void main(String[] args) {

        SpringApplication.run(ArticleController.class, args);
    }




    @GetMapping()
    public String getarticle(){
        return "hello";
    }


    @GetMapping("/{article}")  //id d un article
    public Article getArticle(@PathVariable("id") String id) {
        // code pour récupérer l'article à partir de l'ID


        //Article article= new Article();

        //returne article;


        Article article = new Article("01","titre",0);
        return article;
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        // code pour créer un nouvel article
        return article;
    }

    @PutMapping("/{id}")
    public Article updatArticle(@PathVariable("id") Long id, @RequestBody Article article) {
        // code pour mettre à jour l'article
        return article;
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable("id") Long id) {
        // code pour supprimer l'article
    }
}
