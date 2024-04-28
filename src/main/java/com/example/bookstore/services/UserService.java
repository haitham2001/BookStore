package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.User;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.bookstore.StaticData.addTheseUsers;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> getALL() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(userExists)
            throw new IllegalStateException("Cannot create user: User already exists");
        userRepository.save(user);
    }

    public void deleteUser(String userEmail) {
        Optional<User> user = userRepository.findByEmail(userEmail);

        if(user.isEmpty())
            throw new IllegalStateException("No User with that email exists");

        userRepository.deleteById(user.get().getId());
    }

    @Transactional
    public void updateUser(
            String userEmail,
            String name,
            String email,
            String password
    ) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(
                        () -> new IllegalStateException("No User with that email exists")
                );
        if(userRepository.findByEmail(email).isPresent())
            throw new IllegalStateException("Can't update to that email: Email already exists");

        if(name != null && !name.isEmpty())
            user.setFullName(name);
        if(email != null && !email.isEmpty())
            user.setEmail(email);
        if(password != null && !password.isEmpty())
            user.setPassword(password);
    }

    @Transactional
    public void addBookToUser(Integer bookId, Integer userId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("Book not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));

        int quantity = book.getAvailableQuantity();
        if(quantity == 0)
            throw new IllegalStateException("Book is out of stock");
        book.setAvailableQuantity(quantity - 1);

        user.getOwnedBooks().add(book);
    }

    @Transactional
    public void removeBookInUser(Integer bookId, Integer userId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("Book not found"));

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        user.getOwnedBooks().remove(book);
    }
}
