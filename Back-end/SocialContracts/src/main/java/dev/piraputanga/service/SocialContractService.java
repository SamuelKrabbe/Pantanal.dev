package dev.piraputanga.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import dev.piraputanga.model.SocialContract;
import dev.piraputanga.repository.SocialContractRepository;

import org.springframework.stereotype.Service;

@Service
public class SocialContractService {

    @Autowired
    private SocialContractRepository repository;

    // CRUD

    // CREATE
    public SocialContract createSocialContract(SocialContract socialContract) throws DuplicateKeyException {
        if (this.repository.existsBySocialActionId(socialContract.getSocialActionId()) && this.repository.existsByUserEmail(socialContract.getUserEmail()))
            throw new DuplicateKeyException("contrato cadastrado");
        return this.repository.save(socialContract);
    }

    // READ
    public Optional<SocialContract> getSocialContract(Long id) {
        return this.repository.findById(id);
    }

    // // UPDATE
    // public SocialContract updateById(Long id, SocialContract socialContract) throws ResponseStatusException {
    //     Optional<SocialContract> socialContractOptional = this.repository.findById(id);
    //     if (socialContractOptional.isPresent()) {
    //         socialContract.setId(id);
    //         return this.repository.save(socialContract);
    //     } else {
    //         throw new ResponseStatusException(HttpStatusCode.valueOf(404));
    //     }
    // }

    // DELETE
    public void deleteSocialContract(Long id) {
        this.repository.deleteById(id);
    }

    // SEARCH

    public List<SocialContract> findSocialContracts(String email) {
        if (email == null || email.isEmpty())
            return this.repository.findAll();
        else
            return this.repository.findByUserEmailContainingIgnoreCase(email);
    }

    public List<SocialContract> findSocialContractsBySocialAction(Long id) {
        if (id == null)
            return this.repository.findAll();
        else
            return this.repository.findBySocialActionId(id);
    }

}
