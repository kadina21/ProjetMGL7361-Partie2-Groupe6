package com.example.newspaper.controller;

import com.example.newspaper.outils.GestionAPI;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScientifiqueController {

    @CrossOrigin
    @GetMapping("/users")
    public List<JSONObject> selectAllUsers(){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONUsers();
    }

    @CrossOrigin
    @GetMapping("/users/{type_user}")
    public List<JSONObject> selectTypeUser(@PathVariable(value = "type_user")String type){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONTypeUser(type);
    }

    @CrossOrigin
    @GetMapping("/user/{id_user}")
    public JSONObject selectIDUser(@PathVariable(value = "id_user")String id){
        GestionAPI gapi=GestionAPI.getInstance();
        return gapi.encodeJSONSpecificUser(id);
    }
}
