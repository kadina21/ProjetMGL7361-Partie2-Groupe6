package com.example.journal.scientifique;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScientifiqueService {
    private final ScientifiqueRepository sciRepo;

    @Autowired
    public ScientifiqueService(ScientifiqueRepository scientifiqueRepository){
        sciRepo=scientifiqueRepository;
    }
    public List<Scientifique> getScientifique(){
        return sciRepo.findAll();
    }

    public Optional<Scientifique> getScientifiqueById(Long id){
        return sciRepo.findById(id);
    }

    public Optional<Scientifique> getScientifiqueParType(String type) {
        return sciRepo.findScientifiqueByType(type);
    }

    public Optional<Scientifique> getScientifiqueParUsername(String username){
        return sciRepo.findScientifiqueByUsername(username);
    }

    public Optional<Scientifique> getScientifiqueParLoggedIn(boolean logged) {
        return sciRepo.findScientifiqueByLoggedIn(logged);
    }

    public void addNewStudent(Scientifique s) {
        Optional<Scientifique> sciParEmail=sciRepo.findScientifiqueByEmail(s.getEmail());
        if(sciParEmail.isPresent()){
            throw new IllegalStateException("Email déjà pris");
        }
        Optional<Scientifique> sciParUsername=sciRepo.findScientifiqueByUsername(s.getUsername());
        if(sciParUsername.isPresent()){
            throw new IllegalStateException("Username déjà pris");
        }
        sciRepo.save(s);
    }

    public void deleteScientifique(Long id) {
        boolean existe=sciRepo.existsById(id);
        if(!existe){
            throw new IllegalStateException("Scientifique "+id+" n'existe pas");
        }
        sciRepo.deleteById(id);
    }

    @Transactional
    public void updateScientifique(Long id, String domaine, String emploi, String email, String password) {
        Scientifique s = sciRepo.findById(id).orElseThrow(()->new IllegalStateException("Scientifique "+id+" n'existe pas"));
        if(domaine!=null && domaine.length()>0 && !Objects.equals(s.getDomaine(),domaine)){
            s.setDomaine(domaine);
        }
        if(email!=null && email.length()>0 && !Objects.equals(s.getEmail(),email)){
            Optional<Scientifique> scientifiqueOptional=sciRepo.findScientifiqueByEmail(email);
            if(scientifiqueOptional.isPresent()){
                throw new IllegalStateException("Email déjà pris");
            }
            s.setEmail(email);
        }
        if(emploi!=null && emploi.length()>0 && !Objects.equals(s.getEmploi(),emploi)){
            s.setEmploi(emploi);
        }
        if(password!=null && password.length()>0 && !Objects.equals(s.getPassword(),password)){
            s.setPassword(password);
        }
    }


    public Optional<Scientifique> authentification(String username, String password) {
        Optional<Scientifique> loggedin=sciRepo.findScientifiqueByLoggedIn(true);
        if(loggedin.isPresent()){
            throw new IllegalStateException("Un utilisateur est déjà connecté");
        }
        Optional<Scientifique> os= sciRepo.findScientifiqueByUsernamePassword(username,password);
        if(os.isPresent()){
            Scientifique s=os.get();
            s.setLoggedIn(true);
            os=sciRepo.findScientifiqueByUsernamePassword(username,password);
            return os;
        }else{
            throw new IllegalStateException("Identifiants incorrects");
        }
    }


    public void deconnexion(Long id){
        Scientifique s = sciRepo.findById(id).orElseThrow(()->new IllegalStateException("Scientifique "+id+" n'existe pas"));
        s.setLoggedIn(false);
    }
}

