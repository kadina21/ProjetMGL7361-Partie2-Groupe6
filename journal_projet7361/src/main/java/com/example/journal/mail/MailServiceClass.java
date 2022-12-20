package com.example.journal.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceClass implements MailService{
    @Autowired
    private JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String envoyeur;

    @Override
    public String envoyerMail(DetailsMail infos) {
        try {
            SimpleMailMessage smm = new SimpleMailMessage();
            smm.setFrom(envoyeur);
            smm.setTo(infos.getDestinataire());
            smm.setText(infos.getMessage());
            smm.setSubject(infos.getMessage());
            jms.send(smm);
            return "Envoi réussi";
        } catch (Exception e) {
            return "Echec de l'envoi";
        }
    }
}
