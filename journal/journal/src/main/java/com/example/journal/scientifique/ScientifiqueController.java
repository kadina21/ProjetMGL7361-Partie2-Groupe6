package com.example.journal.scientifique;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scientifique")
public class ScientifiqueController {
    private final ScientifiqueService scientifiqueService;

    @Autowired
    public ScientifiqueController(ScientifiqueService ss){
        this.scientifiqueService=ss;
    }

    @CrossOrigin
    @GetMapping
    public List<Scientifique> getScientifique(){
        return scientifiqueService.getScientifique();
    }

    @CrossOrigin
    @GetMapping(path="{id_scientifique}")
    public Optional<Scientifique> getScientifiqueById(@PathVariable(value="id_scientifique")Long id){
        return scientifiqueService.getScientifiqueById(id);
    }

    @CrossOrigin
    @GetMapping(path="?type{type_user}")
    public Optional<Scientifique> getScientifiqueParType(@PathVariable(value="type_user")String type) {
        return scientifiqueService.getScientifiqueParType(type);
    }

    /*@CrossOrigin
    @GetMapping(path="?type{type_user}")
    public Optional<Scientifique> getScientifiqueAvecAuthentification(@PathVariable(value="type_user")String type) {
        return scientifiqueService.getScientifiqueParType(type);
    }*/

    @CrossOrigin
    @PostMapping
    public void registerNewScientifique(@RequestBody Scientifique s){
        scientifiqueService.addNewStudent(s);
    }

    @CrossOrigin
    @DeleteMapping(path="{id_scientifique}")
    public void deleteScientifique(@PathVariable("id_scientifique")Long id){
        scientifiqueService.deleteScientifique(id);
    }

    @CrossOrigin
    @PutMapping(path="{id_scientifique}")
    public void updateScienfitique(@PathVariable("id_scientifique")Long id,
                                   @RequestParam(required=false)String domaine,
                                   @RequestParam(required = false)String emploi,
                                   @RequestParam(required = false)String email,
                                   @RequestParam(required = false)String password){
        scientifiqueService.updateScientifique(id,domaine,emploi,email,password);
    }

    @CrossOrigin
    @RequestMapping(path="login")
    public Optional<Scientifique> authentification(@RequestParam String username,
                                @RequestParam String password){
        return scientifiqueService.authentification(username,password);
    }

    @CrossOrigin
    @RequestMapping(path="logoff")
    public void deconnexion(@RequestParam Long id){
        scientifiqueService.deconnexion(id);
    }
}