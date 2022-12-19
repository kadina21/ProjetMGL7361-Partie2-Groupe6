package com.example.journal.scientifique;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScientifiqueRepository extends JpaRepository<Scientifique,Long> {
    @Query("SELECT s from Scientifique s where s.email=?1")
    Optional<Scientifique> findScientifiqueByEmail(String email);
    @Query("SELECT s from Scientifique s where s.username=?1")
    Optional<Scientifique> findScientifiqueByUsername(String username);
    @Query("SELECT s from Scientifique s where s.password=?1")
    Optional<Scientifique> findScientifiqueByPassword(String password);
    @Query("SELECT s from Scientifique s where s.typeUser=?1")
    List<Scientifique> findScientifiqueByType(String type_user);
    @Query("SELECT s from Scientifique s where s.loggedIn=?1")
    Optional<Scientifique> findScientifiqueByLoggedIn(boolean logged);
    @Query("SELECT s from Scientifique s where s.username=?1 and s.password=?2")
    Optional<Scientifique> findScientifiqueByUsernamePassword(String username,String password);
}
