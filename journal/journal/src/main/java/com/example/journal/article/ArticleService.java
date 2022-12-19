package com.example.journal.article;

import com.example.journal.comite.Comite;
import com.example.journal.comite.ComiteRepository;
import com.example.journal.scientifique.Scientifique;
import com.example.journal.scientifique.ScientifiqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository artRepo;
    private final ScientifiqueRepository sciRepo;
    private final ComiteRepository comRepo;

    @Autowired
    public ArticleService(ArticleRepository articleRepository, ScientifiqueRepository sciRepo, ComiteRepository comRepo){
        artRepo=articleRepository;
        this.sciRepo = sciRepo;
        this.comRepo = comRepo;
    }
    public List<Article> getArticle(){
        return artRepo.findAll();
    }

    public Optional<Article> getArticleById(Long id){
        return artRepo.findById(id);
    }

    public List<Article> getArticleParCategorie(String cat) {
        return artRepo.findArticleByCategorie(cat);
    }

    public Optional<Article> getArticleParTitre(String titre){
        return artRepo.findArticleByTitre(titre);
    }

    public List<Article> getArticleParEtat(String etat){
        return artRepo.findArticleByEtat(etat);
    }

    public Optional<Article> getArticleParAuteurs(List<Scientifique> auteurs){
        return artRepo.findArticleByAuteurs(auteurs);
    }

    public void addNewArticle(Article a) {
        if(a.getAuteurs().get(0).isLoggedIn()){
            Optional<Comite> oc = comRepo.findComiteByCategorie(a.getCategorie());
            if(oc.isPresent()){
                a.setComite(oc.get());
                a.setEtat(new EtatRelecture());
            }
            artRepo.save(a);
        }else{
            System.out.println("Veuillez vous connecter");
        }

    }

    public void deleteArticle(Long id) {
        boolean existe=artRepo.existsById(id);
        if(!existe){
            throw new IllegalStateException("Article "+id+" n'existe pas");
        }
        artRepo.deleteById(id);
    }

    @Transactional
    public void updateArticle(Long id, String titre, String categorie, List<Scientifique> auteurs) {
        Article a = artRepo.findById(id).orElseThrow(()->new IllegalStateException("Article "+id+" n'existe pas"));
        if(titre!=null && titre.length()>0 && !Objects.equals(a.getTitre(),titre)){
            a.setTitre(titre);
        }
        if(categorie!=null && categorie.length()>0 && !Objects.equals(a.getCategorie(),categorie)){
            a.setCategorie(categorie);
        }
        if(auteurs!=null && auteurs.size()>0 && !Objects.equals(a.getAuteurs(),auteurs)){
            a.setAuteurs(auteurs);
        }
    }

    @Transactional
    public Article updatePointsArticle(Long id, int points) {
        Article a = artRepo.findById(id).orElseThrow(()->new IllegalStateException("Article "+id+" n'existe pas"));
        if(points>=4){
            a.setPoints(points);
            a.setEtat(new EtatCameraReady());
        }
        return a;
    }

}
