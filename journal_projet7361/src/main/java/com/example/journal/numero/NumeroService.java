package com.example.journal.numero;

import com.example.journal.article.ArticleRepository;
import com.example.journal.scientifique.Scientifique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumeroService {
    private final NumeroRepository numRepo;
    private final ArticleRepository artRepo;

    @Autowired
    public NumeroService(NumeroRepository numRepo, ArticleRepository artRepo) {
        this.numRepo = numRepo;
        this.artRepo = artRepo;
    }

    public List<Numero> getNumero(){
        return numRepo.findAll();
    }
}
