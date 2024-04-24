package com.example.bookstore.book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
}


//void dothis(){
//    List<Book> all = List.of(
//            new Book(
//                    "Macbeth",
//                    "me",
//                    "978-3-16-148410-0",
//                    "horror",
//                    "very scary book",
//                    "also me",
//                    LocalDate.of(2000, Month.NOVEMBER, 20),
//                    2500.0,
//                    20
//            ),
//            new Book(
//                    "tale",
//                    "me",
//                    "978-3-16-148410-0",
//                    "horror",
//                    "very scary book",
//                    "also me",
//                    LocalDate.of(2000, Month.NOVEMBER, 20),
//                    2500.0,
//                    20
//            )
//    );
//    repository.saveAll(all);
//}