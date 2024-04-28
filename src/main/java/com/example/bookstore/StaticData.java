package com.example.bookstore;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.User;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StaticData {

    UserRepository userRepository;
    BookRepository bookRepository;

    @Autowired
    public StaticData(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public void addAll(){
        bookRepository.saveAll(addTheseBooks());
        userRepository.saveAll(addTheseUsers());
    }

    public static List<Book> addTheseBooks() {
        return List.of(
                new Book(
                        "Macbeth",
                        "me",
                        "552-3-41-545410-8",
                        "horror",
                        2500.0,
                        2
                ),
                new Book(
                        "tale",
                        "you",
                        "978-3-16-148410-0",
                        "comedy",
                        1250.0,
                        58
                )
        );
    }

    public static List<User> addTheseUsers(){
        return List.of(
                new User("hello", "hello@gmail.com", "hello123"),
                new User("no", "iamot@gmail.com", "aojfgkajgf3")
        );
    }
}
