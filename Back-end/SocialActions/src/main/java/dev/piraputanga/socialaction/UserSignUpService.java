package dev.piraputanga.socialaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserSignUpService {

    @Autowired
    private UserSignUpRepository repository;

    public UserSignUp createUserSignUp(UserSignUp userSignUp) {
        if (repository.existsById(userSignUp.getCpf())) {
            throw new DuplicateKeyException("User with this CPF already exists.");
        }
        return repository.save(userSignUp);
    }

    public Optional<UserSignUp> getUserSignUp(String cpf) {
        return repository.findById(cpf);
    }

    public UserSignUp updateByCpf(String cpf, UserSignUp userSignUp) {
        Optional<UserSignUp> userSignUpOptional = repository.findById(cpf);
        if (userSignUpOptional.isPresent()) {
            userSignUp.setCpf(cpf);
            return repository.save(userSignUp);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with CPF: " + cpf);
        }
    }

    public void deleteUserSignUp(String cpf) {
        repository.deleteById(cpf);
    }

    public List<UserSignUp> findUserSignUps(String username) {
        if (username == null || username.isEmpty())
            return this.repository.findAll();
        else
            return this.repository.findByUsernameContainingIgnoreCase(username);
    }

}
