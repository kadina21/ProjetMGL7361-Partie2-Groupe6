package com.example.journal.numero;

import com.example.journal.article.ArticleService;
import com.example.journal.scientifique.Scientifique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NumeroController {
    private final NumeroService numeroService;
    private final ArticleService articleService;

    @Autowired
    public NumeroController(NumeroService numeroService, ArticleService articleService) {
        this.numeroService = numeroService;
        this.articleService = articleService;
    }

    @CrossOrigin
    @GetMapping(path = "numeros")
    public List<Numero> getNumero(){
        return numeroService.getNumero();
    }
}
