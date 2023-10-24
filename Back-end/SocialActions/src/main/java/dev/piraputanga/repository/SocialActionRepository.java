package dev.piraputanga.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import dev.piraputanga.model.SocialAction;

public interface SocialActionRepository extends ListCrudRepository<SocialAction, Long> {
    public List<SocialAction> findByNameContainingIgnoreCase(String texto);
}