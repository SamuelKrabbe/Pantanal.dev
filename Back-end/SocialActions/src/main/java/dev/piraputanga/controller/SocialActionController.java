package dev.piraputanga.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
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

import jakarta.validation.Valid;

import dev.piraputanga.model.SocialAction;
import dev.piraputanga.dto.SocialActionDTO;
import dev.piraputanga.dto.CreateSocialActionDTO;
import dev.piraputanga.service.SocialActionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/socialactions")
public class SocialActionController {

    @Autowired
    private SocialActionService service;

    private SocialActionDTO convertToDTO(SocialAction socialaction) {
        return SocialActionDTO.builder()
                .id(socialaction.getId())
                .name(socialaction.getName())
                .description(socialaction.getDescription())
                .status(socialaction.getStatus())
                .startDate(socialaction.getStartDate())
                .endDate(socialaction.getEndDate())
                .build();
    }

    private SocialAction convertToEntity(CreateSocialActionDTO socialActionDTO){
        return SocialAction.builder()
                .name(socialActionDTO.getName())
                .description(socialActionDTO.getDescription())
                .status(socialActionDTO.getStatus())
                .startDate(socialActionDTO.getStartDate())
                .endDate(socialActionDTO.getEndDate())
                .build();
    }
    
    @GetMapping
    @Operation(summary = "Lista todos as ações sociais", description = "lista todos as ações sociais(admin restricted)")
    @ApiResponse(responseCode = "200", description = "lista de ações sociais")
    public Collection<SocialActionDTO> getAllSocialActions(
            @RequestParam(value = "name", required = false) String texto) {
        return this.service.findSocialActions(texto).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Busca ação social pelo id", description = "busca uma ação social pelo seu id")
    @ApiResponse(responseCode = "200", description = "Uma ação social, buscada pelo id")
    public ResponseEntity<SocialActionDTO> getSocialAction(@PathVariable(value = "id", required = true) Long id) {
        var socialAction = this.service.getSocialAction(id);
        if (socialAction.isPresent()) {
            return ResponseEntity.ok(convertToDTO(socialAction.get()));
        }
        return ResponseEntity.notFound().build(); 
    }

    @PostMapping
    @Operation(summary = "Cria uma nova ação social", description = "Cria uma nova ação social e retorna a mesma")
    @ApiResponses( value = { 
        @ApiResponse(responseCode = "200", description = "ação social criada"),
        @ApiResponse(responseCode = "409", description = "ação social com mesmo nome já existe")
    })
    public ResponseEntity<SocialActionDTO> createSocialAction(@Valid @RequestBody CreateSocialActionDTO socialaction) {
        try {
            var result = this.service.createSocialAction(convertToEntity(socialaction));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Atualiza uma ação social", description = "Atualiza uma ação social através do seu id(admin restricted)")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "ação social atualizada"),
        @ApiResponse(responseCode = "409", description = "erro de conflito")
    })
    public ResponseEntity<SocialActionDTO> updateSocialAction(@PathVariable(value = "id", required = true) Long id,
            @Valid @RequestBody CreateSocialActionDTO socialAction) {
        try {
            var result = this.service.updateById(id, convertToEntity(socialAction));
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(result));
        } catch (ResponseStatusException rse) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deleta uma ação social", description = "Deleta uma ação social através do seu id(admin restricted)")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "ação social deletada"),
        @ApiResponse(responseCode = "404", description = "ação social não encontrada")
    })
    public ResponseEntity<?> deleteSocialAction(@PathVariable(value = "id", required = true) Long id) {
        var socialAction = this.service.getSocialAction(id);
        if (socialAction.isPresent()) {
            this.service.deleteSocialAction(socialAction.get().getId());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
