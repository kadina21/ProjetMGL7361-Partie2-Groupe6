package com.example.journal.evaluateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComiteService {
    private final ComiteRepository comRepo;

    @Autowired
    public ComiteService(ComiteRepository comRepository){
        comRepo=comRepository;
    }

    public List<Comite> getComite() {
        return comRepo.findAll();
    }

    public Optional<Comite> getComiteByCategorie(String cat) {
        return comRepo.findComiteByCategorie(cat);
    }
}
