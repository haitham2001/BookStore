package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getALlBooks() {
        dothis();
        return repository.findAll();
    }

    void dothis() {
        List<Book> all = List.of(
                new Book(
                        "Macbeth",
                        "me",
                        "978-3-16-148410-0",
                        "horror",
                        2500.0,
                        20
                ),
                new Book(
                        "tale",
                        "me",
                        "978-3-16-148410-0",
                        "horror",
                        2500.0,
                        20
                )
        );
        repository.saveAll(all);
    }
}
