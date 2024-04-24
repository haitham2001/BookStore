package com.example.bookstore.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
public class BookController {

    @GetMapping(path = "books")
    public List<Book> getAllBooks(){
        return List.of(
                new Book(
                        "Macbeth",
                        "me",
                        "978-3-16-148410-0",
                        "horror",
                        "very scary book",
                        "also me",
                        LocalDate.of(2000, Month.NOVEMBER, 20),
                        2500.0,
                        20
                ),
                new Book(
                        "tale",
                        "me",
                        "978-3-16-148410-0",
                        "horror",
                        "very scary book",
                        "also me",
                        LocalDate.of(2000, Month.NOVEMBER, 20),
                        2500.0,
                        20
                )

        );
    }
}
