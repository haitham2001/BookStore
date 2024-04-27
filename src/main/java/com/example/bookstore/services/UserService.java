package com.example.bookstore.services;

import com.example.bookstore.models.User;
import com.example.bookstore.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.bookstore.StaticData.addTheseBooks;
import static com.example.bookstore.StaticData.addTheseUsers;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getALL() {
        return repository.findAll();
    }

    public void createUser(User user) {
        boolean userExists = repository.findByEmail(user.getEmail()).isPresent();

        if(userExists)
            throw new IllegalStateException("Cannot create user: User already exists");
        repository.save(user);
    }

    public void deleteUser(String userEmail) {
        Optional<User> user = repository.findByEmail(userEmail);

        if(user.isEmpty())
            throw new IllegalStateException("No User with that email exists");

        repository.deleteById(user.get().getId());
    }

    @Transactional
    public void updateUser(
            String userEmail,
            String name,
            String email,
            String password
    ) {
        User user = repository.findByEmail(userEmail)
                .orElseThrow(
                        () -> new IllegalStateException("No User with that email exists")
                );
        if(repository.findByEmail(email).isPresent())
            throw new IllegalStateException("Can't update to that email: Email already exists");

        if(name != null && !name.isEmpty())
            user.setFullName(name);
        if(email != null && !email.isEmpty())
            user.setEmail(email);
        if(password != null && !password.isEmpty())
            user.setPassword(password);

    }



    public void addStatic() {
        repository.saveAll(addTheseUsers());
    }
}
