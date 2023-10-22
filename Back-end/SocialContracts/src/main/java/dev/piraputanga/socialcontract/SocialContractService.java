package dev.piraputanga.socialcontract;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Service
public class SocialContractService {

    @Autowired
    private SocialContractRepository repository;

    // CRUD

    // CREATE
    public SocialContract createSocialContract(SocialContract socialContract) throws DuplicateKeyException {
        if (this.repository.existsById(socialContract.getId()))
            throw new DuplicateKeyException("contrato cadastrado");
        return this.repository.save(socialContract);
    }

    // READ
    public Optional<SocialContract> getSocialContract(String id) {
        return this.repository.findById(id);
    }

    // UPDATE
    public SocialContract updateById(String id, SocialContract socialContract) throws ResponseStatusException {
        Optional<SocialContract> socialContractOptional = this.repository.findById(id);
        if (socialContractOptional.isPresent()) {
            socialContract.setId(id);
            return this.repository.save(socialContract);
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404));
        }
    }

    // DELETE
    public void deleteSocialContract(String id) {
        this.repository.deleteById(id);
    }

    // SEARCH

    public List<SocialContract> findSocialContracts(String texto) {
        if (texto == null || texto.isEmpty())
            return this.repository.findAll();
        else
            return this.repository.findByUserEmailContainingIgnoreCase(texto);
    }

    public List<SocialContract> findSocialContractsBySocialAction(String texto) {
        if (texto == null || texto.isEmpty())
            return this.repository.findAll();
        else
            return this.repository.findBySocialActionIdEmailContainingIgnoreCase(texto);
    }

}
