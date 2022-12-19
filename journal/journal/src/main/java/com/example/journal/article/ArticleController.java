package com.example.journal.article;

import com.example.journal.scientifique.Scientifique;
import com.example.journal.scientifique.ScientifiqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService as){
        this.articleService=as;
    }

    @CrossOrigin
    @GetMapping("articles")
    public List<Article> getArticle(){
        return articleService.getArticle();
    }

    @CrossOrigin
    @GetMapping(path="article/{id_article}")
    public Optional<Article> getArticleById(@PathVariable(value="id_article")Long id){
        return articleService.getArticleById(id);
    }

    @CrossOrigin
    @GetMapping(path="article_cat/{categorie}")
    public List<Article> getArticleParCategorie(@PathVariable(value="categorie")String cat) {
        return articleService.getArticleParCategorie(cat);
    }

    @CrossOrigin
    @GetMapping(path="article_etat/{etat}")
    public List<Article> getArticleParEtat(@PathVariable(value="etat")String etat) {
        return articleService.getArticleParEtat(etat);
    }

    @CrossOrigin
    @PostMapping("article")
    public void registerNewArticle(@RequestBody Article a){
        articleService.addNewArticle(a);
    }

    @CrossOrigin
    @DeleteMapping(path="{id_article}")
    public void deleteArticle(@PathVariable("id_article")Long id){
        articleService.deleteArticle(id);
    }

    @CrossOrigin
    @PutMapping(path="{id_article}")
    public void updateArticle(@PathVariable("id_article")Long id,
                                   @RequestParam(required=false)String titre,
                                   @RequestParam(required = false)String categorie,
                                   @RequestParam(required = false)List<Scientifique> auteurs){
        articleService.updateArticle(id,titre,categorie,auteurs);
    }


}
