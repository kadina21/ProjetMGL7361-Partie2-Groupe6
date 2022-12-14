package com.example.newspaper.outils;

import org.json.simple.JSONObject;

import java.util.List;

public interface APIUser {
    public void decodeJSONUser(String json);
    public JSONObject encodeJSONSpecificUser(String id);
    public List<JSONObject> encodeJSONUsers();
    public List<JSONObject> encodeJSONTypeUser(String typeUser);

}
