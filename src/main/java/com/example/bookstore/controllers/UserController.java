package com.example.bookstore.controllers;

import com.example.bookstore.models.User;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<User> getAllUsers(){
        return service.getALL();
    }

    @GetMapping("addStatic")
    public void addStatic(){
        service.addStatic();
    }

    @PostMapping()
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }

    @DeleteMapping(path = "{userEmail}")
    public void deleteUser(@PathVariable("userEmail") String userEmail){
        service.deleteUser(userEmail);
    }

    @PutMapping(path = "{userEmail}")
    public void updateUser(
            @PathVariable("{userEmail}") String userEmail,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password
    ){
        service.updateUser(userEmail, name, email, password);
    }
}
