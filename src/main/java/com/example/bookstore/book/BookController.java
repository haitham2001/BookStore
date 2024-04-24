package com.example.bookstore.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/books")
public class BookController {

    BookService service;

    @Autowired // For Dependency Injection
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping(path = "get-all")
    public List<Book> getAllBooks(){
        return service.getALlBooks();
    }
}
