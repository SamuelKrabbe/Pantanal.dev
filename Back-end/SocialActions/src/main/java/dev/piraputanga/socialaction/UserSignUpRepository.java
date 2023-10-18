package dev.piraputanga.socialaction;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface UserSignUpRepository extends ListCrudRepository<UserSignUp, String> {
    public List<UserSignUp> findByUsernameContainingIgnoreCase(String texto);
}