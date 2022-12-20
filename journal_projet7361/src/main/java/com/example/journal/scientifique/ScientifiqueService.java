package com.example.journal.scientifique;

import com.example.journal.article.Article;
import com.example.journal.article.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ScientifiqueService {
    private final ScientifiqueRepository sciRepo;
    private final ArticleRepository artRepo;

    @Autowired
    public ScientifiqueService(ScientifiqueRepository scientifiqueRepository, ArticleRepository artRepo){
        sciRepo=scientifiqueRepository;
        this.artRepo = artRepo;
    }

    public List<Scientifique> getScientifique(){
        return sciRepo.findAll();
    }

    public Optional<Scientifique> getScientifiqueById(Long id){
        return sciRepo.findById(id);
    }

    public List<Scientifique> getScientifiqueParType(String type) {
        /*List<ScientifiqueDTO> ls =new ArrayList<>();
        for (Scientifique s : {
            ScientifiqueDTO sd=new ScientifiqueDTO(s);
            System.out.println(sd);
            ls.add(sd);
        }
        return ls;*/
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

    @Transactional
    public Optional<Scientifique> authentification(AuthentificationPOJO apojo) {
        Optional<Scientifique> loggedin=sciRepo.findScientifiqueByLoggedIn(true);
        if(loggedin.isPresent()){
            throw new IllegalStateException("Un utilisateur est déjà connecté");
        }
        if(apojo.getUsername()!=null && apojo.getPassword()!=null){
            Optional<Scientifique> os= sciRepo.findScientifiqueByUsernamePassword(apojo.getUsername(),apojo.getPassword());
            if(os.isPresent()){
                Scientifique s=os.get();
                s.setLoggedIn(true);
                os=sciRepo.findScientifiqueByUsernamePassword(apojo.getUsername(),apojo.getPassword());
                return os;
            }else{
                throw new IllegalStateException("Identifiants incorrects");
            }
        }else{
            throw new IllegalStateException("Identifiants nuls");
        }

    }


    @Transactional
    public void deconnexion(Long id){
        Scientifique s = sciRepo.findById(id).orElseThrow(()->new IllegalStateException("Scientifique "+id+" n'existe pas"));
        s.setLoggedIn(false);
    }

    public String getEtatArticle(Long id_sci,Long id_art) {
        Optional<Scientifique> os = sciRepo.findById(id_sci);
        Optional<Article> oa = artRepo.findById(id_art);
        if(os.isPresent() && oa.isPresent()){
            List<Article> la=os.get().getArticles();
            Article a = oa.get();
            Scientifique s=os.get();
            if(s.isLoggedIn()){
                if(la.contains(a)){
                    return a.getEtat();
                }
            }else{
                return "Veuillez vous connecter";
            }
        }else{
            throw new IllegalStateException("Scientifique ou article introuvable");
        }
        return null;
    }
}

