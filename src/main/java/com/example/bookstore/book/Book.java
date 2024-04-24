package com.example.bookstore.book;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Book {

    @Id
    @SequenceGenerator(
            name = "incrementSequence",
            sequenceName = "incrementSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "incrementSequence"
    )
    private Integer id;

    private String title;
    private String author;
    private String isbn;
    private String genre;
    private String description;
    private String publisher;
    private LocalDate publicationYear;
    private Double price;
    private Integer availableQuantity;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Book(
            String title,
            String author,
            String isbn,
            String genre,
            String description,
            String publisher,
            LocalDate publicationYear,
            Double price,
            Integer availableQuantity
    ) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.description = description;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

    public Book(
            String title,
            String author,
            String isbn
    ) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
}
