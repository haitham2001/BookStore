package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.bookstore.StaticData.addTheseBooks;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getALLBooks() {
        return repository.findAll();
    }

    public Book getBookById(Integer id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Book does not exist")
                );
    }

    public void deleteBook(Integer id) {
        Book book = repository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Book does not exist")
                );
        repository.deleteById(id);
    }

    public void deleteALlBooks() {
        if(!repository.findAll().isEmpty())
            repository.deleteAll();
        else
            throw new IllegalStateException("Database does not have any books");
    }

    public void addBook(Book book) {
        Optional<Book> databaseBook = repository.findByISBN(book.getIsbn());
        if (databaseBook.isPresent())
            throw new IllegalStateException("Book already exists in database");
        repository.save(book);
    }

    public List<Book> filterBook(
            String title,
            String author,
            String genre
    ) {
        Book book = Book
                .builder()
                .title(title)
                .genre(genre)
                .author(author)
                .build();

        return repository.findAll(Example.of(book));
    }
}
