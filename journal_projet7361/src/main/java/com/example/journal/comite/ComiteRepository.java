package com.example.journal.comite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComiteRepository extends JpaRepository<Comite,Long> {
    //@Query("SELECT s from Comite c, Scientifique where s in c.listEval=?1")
    //Optional<Comite> findComiteByDomaine(String categorie);
    @Query("SELECT c from Comite c where c.id=?1")
    Optional<Comite> findComiteByCategorie(String cat);
}
