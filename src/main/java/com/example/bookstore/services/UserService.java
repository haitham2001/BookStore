package com.example.bookstore.services;

import com.example.bookstore.models.User;
import com.example.bookstore.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getALL() {
        dothis();
        return repository.findAll();
    }

    private void dothis(){
        List<User> all = List.of(
                new User("hello", "hello@gmail.com", "hello123"),
                new User("no", "i am ot@gmail.com", "aojfgkajgf3")
        );
        repository.saveAll(all);
    }
}
