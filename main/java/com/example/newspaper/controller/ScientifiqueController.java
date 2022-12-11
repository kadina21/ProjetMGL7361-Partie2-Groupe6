package com.example.newspaper.controller;

import com.example.newspaper.objets.Scientifique;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.newspaper.json.GestionAPI;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScientifiqueController {

    @GetMapping("/users")
    public List<JSONObject> selectAllUsers(){
        return GestionAPI.encodeJSONUsers();
    }

    @GetMapping("/users/{type_user}")
    public List<JSONObject> selectTypeUser(@PathVariable(value = "type_user")String type){
        return GestionAPI.encodeJSONTypeUser(type);
    }

    @GetMapping("/users_id={id_user}")
    public JSONObject selectIDUser(@PathVariable(value = "id_user")String id){
        return GestionAPI.encodeJSONSpecific(id);
    }
}
