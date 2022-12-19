package com.example.journal.article;

import com.example.journal.scientifique.Scientifique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Query("SELECT a from Article a, Scientifique s where a.titre=?1")
    Optional<Article> findArticleByTitre(String titre);
    @Query("SELECT a from Article a where a.categorie=?1")
    List<Article> findArticleByCategorie(String categorie);
    @Query("SELECT a from Article a where a.etat=?1")
    List<Article> findArticleByEtat(String etat);
    @Query("SELECT a from Article a where a.articles_scientifiques=?1")
    Optional<Article> findArticleByAuteurs(List<Scientifique> auteurs);
}
