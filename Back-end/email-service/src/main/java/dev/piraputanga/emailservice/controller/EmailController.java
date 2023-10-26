package dev.piraputanga.emailservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.piraputanga.emailservice.dto.EmailDTO;
import dev.piraputanga.emailservice.service.EmailService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/sendEmails")
public class EmailController {
    
    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Autowired
    Keycloak keycloak;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmails(@Valid @RequestBody EmailDTO email){
        List<UserRepresentation> users = keycloak.realm(keycloakRealm).users().list();

        List<String> userEmails = users.stream().map(user -> user.getEmail()).toList();

        List<String> errorEmails = new ArrayList<String>();;

        for(int i = 0; i < userEmails.size(); i++){
            try{
                emailService.sendEmail(userEmails.get(i), email.getSubject(), email.getBody());
            } catch (MailException e){
                errorEmails.add(userEmails.get(i));
            }
        }

        if(errorEmails.size() > 0){
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(errorEmails.toString());
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(userEmails.toString());
        }

    }

}