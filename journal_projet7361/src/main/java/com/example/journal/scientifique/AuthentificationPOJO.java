package com.example.journal.scientifique;

public class AuthentificationPOJO {
    private String username;
    private String password;

    public AuthentificationPOJO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthentificationPOJO(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
