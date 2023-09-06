package dev.piraputanga.socialaction;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface SocialActionRepository extends ListCrudRepository<SocialAction, String> {
    public List<SocialAction> findByNameContainingIgnoreCase(String texto);
}