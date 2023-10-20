package dev.piraputanga.socialcontract;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface SocialContractRepository extends ListCrudRepository<SocialContract, String> {
    public List<SocialContract> findByUserEmailContainingIgnoreCase(String texto);
    public List<SocialContract> findBySocialActionIdEmailContainingIgnoreCase(String texto);
}