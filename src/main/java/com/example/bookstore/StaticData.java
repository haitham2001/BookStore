package com.example.bookstore;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.User;

import java.util.List;

public class StaticData {
    public static List<Book> addTheseBooks() {
        return List.of(
                new Book(
                        "Macbeth",
                        "me",
                        "552-3-41-545410-8",
                        "horror",
                        2500.0,
                        20
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
