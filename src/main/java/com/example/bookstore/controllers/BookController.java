package com.example.bookstore.controllers;

import com.example.bookstore.services.BookService;
import com.example.bookstore.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService service;

    @Autowired // For Dependency Injection
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return service.getALLBooks();
    }

    @GetMapping(path = "{bookId}")
    public Book getBookById(@PathVariable("bookId") Integer id){
        return service.getBookById(id);
    }

    @GetMapping(path = "book")
    public List<Book> filterBook(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String genre
    ){
        return service.filterBook(title, author, genre);
    }

    @PostMapping
    public void addBook(@RequestBody Book book){
        service.addBook(book);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Integer id){
        service.deleteBook(id);
    }

    @DeleteMapping
    public void deleteAll(){
        service.deleteALlBooks();
    }
}
