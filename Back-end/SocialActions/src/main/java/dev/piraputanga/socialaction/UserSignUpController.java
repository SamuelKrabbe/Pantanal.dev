package dev.piraputanga.socialaction;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/usersignup")
public class UserSignUpController {

    @Autowired
    private UserSignUpService service;

    private UserSignUpDTO convertToDTO(UserSignUp userSignUp) {
        return UserSignUpDTO.builder()
                .cpf(userSignUp.getCpf())
                .username(userSignUp.getUsername())
                .userEmail(userSignUp.getUserEmail())
                .userCellphoneNumber(userSignUp.getUserCellphoneNumber())
                .sex(userSignUp.getSex())
                .cep(userSignUp.getCep())
                .userPassword(userSignUp.getUserPassword())
                .build();
    }

    private UserSignUp convertToEntity(UserSignUpDTO userSignUp) {
        return UserSignUp.builder()
                .cpf(userSignUp.getCpf())
                .username(userSignUp.getUsername())
                .userEmail(userSignUp.getUserEmail())
                .userCellphoneNumber(userSignUp.getUserCellphoneNumber())
                .sex(userSignUp.getSex())
                .cep(userSignUp.getCep())
                .userPassword(userSignUp.getUserPassword())
                .build();
    }

    @GetMapping
    public Collection<UserSignUpDTO> getAllUserSignUps(
            @RequestParam(value = "username", required = false) String texto) {
        return this.service.findUserSignUps(texto).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/{cpf}")
    public ResponseEntity<UserSignUpDTO> getUserSignUp(@PathVariable(value = "cpf", required = true) String cpf) {
        var userSignUp = this.service.getUserSignUp(cpf);
        if (userSignUp.isPresent()) {
            return ResponseEntity.ok(convertToDTO(userSignUp.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UserSignUpDTO> createUserSignUp(@Valid @RequestBody UserSignUpDTO userSignUp) {
        try {
            var result = this.service.createUserSignUp(convertToEntity(userSignUp));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/{cpf}")
    public ResponseEntity<UserSignUpDTO> updateUserSignUp(@PathVariable(value = "cpf", required = true) String cpf,
            @Valid @RequestBody UserSignUpDTO userSignUp) {
        try {
            var result = this.service.updateByCpf(cpf, convertToEntity(userSignUp));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (ResponseStatusException rse) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path = "/{cpf}")
    public ResponseEntity<?> deleteUserSignUp(@PathVariable(value = "cpf", required = true) String cpf) {
        var userSignUp = this.service.getUserSignUp(cpf);
        if (userSignUp.isPresent()) {
            this.service.deleteUserSignUp(userSignUp.get().getCpf());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
