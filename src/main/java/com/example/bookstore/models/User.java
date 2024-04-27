package com.example.bookstore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "users_SEQ", sequenceName = "users_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_SEQ")
    private Integer id;

    private String fullName;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Invalid email format")
    private String email;
    private String password;

    @OneToMany
    private List<Book> ownedBooks;

    public User() {
    }

    public User(
            String fullName,
            String email,
            String password
    ) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.ownedBooks = Collections.emptyList();
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public void setOwnedBooks(List<Book> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }
}
