package com.example.journal.comite;

import com.example.journal.article.Article;
import com.example.journal.article.ArticleService;
import com.example.journal.article.EtatCameraReady;
import com.example.journal.scientifique.Scientifique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.journal.article.ArticleRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ComiteService {
    private final ComiteRepository comRepo;
    private final ArticleRepository artRepo;
    private final ArticleService articleService;

    @Autowired
    public ComiteService(ComiteRepository comRepository, ArticleRepository artRepo, ArticleService articleService){
        comRepo=comRepository;
        this.artRepo = artRepo;
        this.articleService = articleService;
    }

    public List<Comite> getComite() {
        return comRepo.findAll();
    }

    public Optional<Comite> getComiteByCategorie(String cat) {
        return comRepo.findComiteByCategorie(cat);
    }

    public Comite getComiteByArticle(Long id) {
       Optional<Article> oa =  artRepo.findById(id);
       if(oa.isPresent()){
           Article a=oa.get();
           return a.getComite();
       }else{
           throw new IllegalStateException("Article introuvable");
       }
    }

    public Optional<Article> noterArticle(String id) {
        Optional<Comite> oc=comRepo.findComiteByCategorie(id);
        if(oc.isPresent()){
            Random r=new Random();
            Comite c=oc.get();
            Article a=c.getArticle();
            for(Scientifique s :c.getListEval()){
                int pointsDonnes = r.nextInt(4);
                a.setPoints(a.getPoints()+pointsDonnes);
            }
            if(a.getPoints()>=4){
                Article res=articleService.updatePointsArticle(a.getId(),a.getPoints());
                //artRepo.save(res);
                return articleService.getArticleById(a.getId());
            }else{
                a.setPoints(0);
            }
        }else{
            throw new IllegalStateException("Comite introuvable");
        }
        return null;
    }
}
