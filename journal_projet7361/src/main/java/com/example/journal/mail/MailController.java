package com.example.journal.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    private MailService ms;

    @PostMapping("/envoyerMail")
    public String envoyerMail(@RequestBody DetailsMail infos){
        String statut=ms.envoyerMail(infos);
        return statut;
    }
}
