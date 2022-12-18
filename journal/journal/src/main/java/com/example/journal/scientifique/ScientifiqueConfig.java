package com.example.journal.scientifique;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ScientifiqueConfig {

    @Bean
    CommandLineRunner commandLineRunner(ScientifiqueRepository repo){
        return args ->{
            Scientifique s1=new Scientifique(
                    "Holland",
                    "Tom",
                    "tholland@gmail.com",
                    "tomH",
                    "spidey",
                    "auteur",
                    "Physique",
                    "Chercheur"
            );

            Scientifique s2=new Scientifique(
                    "Oldman",
                    "Barry",
                    "boldman@gmail.com",
                    "barryO",
                    "barrio",
                    "auteur",
                    "Chimie",
                    "Chercheur"
            );

            repo.saveAll(
                    List.of(s1,s2)
            );
        };
    }
}
