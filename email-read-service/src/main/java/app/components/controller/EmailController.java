package app.components.controller;

import app.components.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailController (EmailRepository emailRepository){
        this.emailRepository = emailRepository;
    }
    @GetMapping("/{idUser}")
    public ResponseEntity<?> getEmailByIdUser(@PathVariable("idUser") Long idUser){
        String email = emailRepository.findEmailById(idUser);
        if (email.isEmpty()){
            return new ResponseEntity<>("email don't find with idUser" + idUser, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(email, HttpStatus.OK);
        }
    }
}
