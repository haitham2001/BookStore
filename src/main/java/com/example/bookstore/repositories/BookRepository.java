package com.example.bookstore.repositories;

import com.example.bookstore.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.genre = ?1")
    Optional<List<Book>> findByGenre(String genre);

    @Query("SELECT b FROM Book b WHERE b.isbn = ?1")
    Optional<Book> findByISBN(String isbn);
}
