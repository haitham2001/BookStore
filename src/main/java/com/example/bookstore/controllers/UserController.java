package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
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

    @PostMapping()
    public void createUser(@RequestBody User user){
        service.createUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Integer id){
        service.deleteUser(id);
    }

    @PutMapping(path = "{userId}")
    public void updateUser(
            @PathVariable("userId") Integer id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password
    ){
        service.updateUser(id, name, email, password);
    }

    @PutMapping(path = "{userId}/{bookId}")
    public void addBookToUser(
            @PathVariable("bookId") Integer bookId,
            @PathVariable("userId") Integer userId
    ){
        service.addBookToUser(bookId, userId);
    }

    @GetMapping(path = "{userId}/recommended-books")
    public List<Book> getRecommendation(@PathVariable("userId") Integer id) {
        return service.getRecommendation(id);
    }

    @DeleteMapping(path = "{userId}/{bookId}")
    public void removeBookInUser(
            @PathVariable Integer bookId,
            @PathVariable Integer userId
    ){
        service.removeBookInUser(bookId, userId);
    }
}
