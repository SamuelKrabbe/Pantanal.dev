package dev.piraputanga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import dev.piraputanga.repository.SocialActionRepository;
import dev.piraputanga.model.SocialAction;

@Service
public class SocialActionService {

    @Autowired
    private SocialActionRepository repository;

    // CRUD

    // CREATE
    public SocialAction createSocialAction(SocialAction socialaction) {
        // if (this.repository.existsById(socialaction.getId()))
        //     throw new DuplicateKeyException("ação já cadastrada");
        return this.repository.save(socialaction);
    }

    // READ
    public Optional<SocialAction> getSocialAction(Long id) {
        return this.repository.findById(id);
    }

    // UPDATE
    public SocialAction updateById(Long id, SocialAction socialAction) throws ResponseStatusException {
        Optional<SocialAction> socialActionOptional = this.repository.findById(id);
        if (socialActionOptional.isPresent()) {
            socialAction.setId(id);
            return this.repository.save(socialAction);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    // DELETE
    public void deleteSocialAction(Long id) {
        this.repository.deleteById(id);
    }

    // SEARCH

    public List<SocialAction> findSocialActions(String texto) {
        if (texto == null || texto.isEmpty())
            return this.repository.findAll();
        else
            return this.repository.findByNameContainingIgnoreCase(texto);
    }

}
