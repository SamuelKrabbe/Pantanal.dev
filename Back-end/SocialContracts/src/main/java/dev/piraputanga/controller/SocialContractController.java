package dev.piraputanga.controller;

import java.util.Collection;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import dev.piraputanga.dto.CreateSocialContractDTO;
import dev.piraputanga.dto.SocialContractDTO;
import dev.piraputanga.model.SocialContract;
import dev.piraputanga.service.SocialContractService;
import dev.piraputanga.utils.KeycloakJwtRolesConverter;
import org.springframework.security.oauth2.jwt.Jwt;

import org.springframework.web.bind.annotation.CrossOrigin;


import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://127.0.0.1:5500/")
@RestController
@RequestMapping("/socialcontracts")
public class SocialContractController {

    @Autowired
    private SocialContractService service;

    private SocialContractDTO convertToDTO(SocialContract socialContract) {
        return SocialContractDTO.builder()
                .id(socialContract.getId())
                .userEmail(socialContract.getUserEmail())
                .socialActionId(socialContract.getSocialActionId())
                .build();
    }

    private SocialContract convertToEntity(SocialContractDTO socialContract) {
        return SocialContract.builder()
                .id(socialContract.getId())
                .userEmail(socialContract.getUserEmail())
                .socialActionId(socialContract.getSocialActionId())
                .build();
    }

        private SocialContract convertToEntityCreate(CreateSocialContractDTO socialContract) {
        return SocialContract.builder()
                .userEmail(socialContract.getUserEmail())
                .socialActionId(socialContract.getSocialActionId())
                .build();
    }

    @GetMapping
    public Collection<SocialContractDTO> getAllSocialContracts() {
        return this.service.findSocialContracts(null).stream().map(this::convertToDTO).collect(Collectors.toList());
    }



    @GetMapping(path = "/{actionId}")
    public Collection<SocialContractDTO> getSocialContract(@PathVariable(value = "actionId", required = true) Long id) {
        return this.service.findSocialContractsBySocialAction(id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/usersocialcontracts")
    public Collection<SocialContractDTO> getSocialContractByEmail(Authentication authentication) {
        String userEmail = (authentication.getPrincipal()).getClaim("email");
        return this.service.findSocialContracts(userEmail).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<SocialContractDTO> createSocialContract(@Valid @RequestBody CreateSocialContractDTO socialContract) {
        try {
            var result = this.service.createSocialContract(convertToEntityCreate(socialContract));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SocialContractDTO> updateSocialContract(@PathVariable(value = "id", required = true) Long id,
            @Valid @RequestBody SocialContractDTO socialContract) {
        try {
            var result = this.service.updateById(id, convertToEntity(socialContract));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (ResponseStatusException rse) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSocialContract(@PathVariable(value = "id", required = true) Long id) {
        var socialContract = this.service.getSocialContract(id);
        if (socialContract.isPresent()) {
            this.service.deleteSocialContract(socialContract.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
