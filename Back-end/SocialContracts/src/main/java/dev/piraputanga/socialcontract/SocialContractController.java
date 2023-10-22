package dev.piraputanga.socialcontract;

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
@RequestMapping("/socialactions")
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

    @GetMapping
    public Collection<SocialContractDTO> getAllSocialContracts(
            @RequestParam(value = "name", required = false) String texto) {
        return this.service.findSocialContracts(texto).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public Collection<SocialContractDTO> getSocialContract(@PathVariable(value = "id", required = true) String id) {
        return this.service.findSocialContractsBySocialAction(id).stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @GetMapping(path = "/{email}")
    public Collection<SocialContractDTO> getSocialContractByEmail(@PathVariable(value = "email", required = true) String email) {
        return this.service.findSocialContracts(email).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<SocialContractDTO> createSocialContract(@Valid @RequestBody SocialContractDTO socialContract) {
        try {
            var result = this.service.createSocialContract(convertToEntity(socialContract));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SocialContractDTO> updateSocialContract(@PathVariable(value = "id", required = true) String id,
            @Valid @RequestBody SocialContractDTO socialContract) {
        try {
            var result = this.service.updateById(id, convertToEntity(socialContract));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (ResponseStatusException rse) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteSocialContract(@PathVariable(value = "id", required = true) String id) {
        var socialContract = this.service.getSocialContract(id);
        if (socialContract.isPresent()) {
            this.service.deleteSocialContract(socialContract.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
