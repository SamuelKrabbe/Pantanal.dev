package dev.piraputanga.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import dev.piraputanga.model.SocialContract;

public interface SocialContractRepository extends ListCrudRepository<SocialContract, Long> {
    public List<SocialContract> findByUserEmailContainingIgnoreCase(String texto);
    public List<SocialContract> findBySocialActionId(Long id);
    public Boolean existsByUserEmail(String userEmail);
    public Boolean existsBySocialActionId(Long id);
}