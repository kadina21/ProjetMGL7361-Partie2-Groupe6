package com.example.journal.scientifique;

import com.example.journal.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ScientifiqueController {
    private final ScientifiqueService scientifiqueService;

    @Autowired
    public ScientifiqueController(ScientifiqueService ss){
        this.scientifiqueService=ss;
    }

    @CrossOrigin
    @GetMapping(path = "scientifiques")
    public List<Scientifique> getScientifique(){
        return scientifiqueService.getScientifique();
    }

    @CrossOrigin
    @GetMapping(path="scientifique/{id_scientifique}")
    public Optional<Scientifique> getScientifiqueById(@PathVariable(value="id_scientifique")Long id){
        return scientifiqueService.getScientifiqueById(id);
    }

    @CrossOrigin
    @GetMapping(path="scientifique_type/{type_user}")
    public List<Scientifique> getScientifiqueParType(@PathVariable(value="type_user")String type) {
        return scientifiqueService.getScientifiqueParType(type);
    }

    @CrossOrigin
    @GetMapping(path="scientifique_id/{id_sci}/etat_article/{id_art}")
    public String getEtatArticle(@PathVariable(value = "id_sci")Long id_sci,@PathVariable(value = "id_art")Long id_art) {
        return scientifiqueService.getEtatArticle(id_sci,id_art);
    }

    @CrossOrigin
    @PostMapping("scientifique_add")
    public void registerNewScientifique(@RequestBody Scientifique s){
        scientifiqueService.addNewStudent(s);
    }

    @CrossOrigin
    @DeleteMapping(path="scientifique_del/{id_scientifique}")
    public void deleteScientifique(@PathVariable("id_scientifique")Long id){
        scientifiqueService.deleteScientifique(id);
    }

    @CrossOrigin
    @PutMapping(path="scientifique_maj/{id_scientifique}")
    public void updateScienfitique(@PathVariable("id_scientifique")Long id,
                                   @RequestParam(required=false)String domaine,
                                   @RequestParam(required = false)String emploi,
                                   @RequestParam(required = false)String email,
                                   @RequestParam(required = false)String password){
        scientifiqueService.updateScientifique(id,domaine,emploi,email,password);
    }

    @CrossOrigin
    @PostMapping(path="login")
    public Optional<Scientifique> authentification(@RequestBody AuthentificationPOJO apojo){
        return scientifiqueService.authentification(apojo);
    }

    @CrossOrigin
    @RequestMapping(path="logoff")
    public void deconnexion(@RequestParam Long id){
        scientifiqueService.deconnexion(id);
    }
}