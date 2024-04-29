package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.User;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;

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

    public void deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);

        if(user.isEmpty())
            throw new IllegalStateException("This user doesn't exist");

        userRepository.deleteById(user.get().getId());
    }

    public void updateUser(
            Integer id,
            String name,
            String email,
            String password
    ) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("This user doesn't exist")
                );
        if(userRepository.findByEmail(email).isPresent())
            throw new IllegalStateException("Can't update to that email: Email already exists");

        if(name != null && !name.isEmpty())
            user.setFullName(name);
        if(email != null && !email.isEmpty())
            user.setEmail(email);
        if(password != null && !password.isEmpty())
            user.setPassword(password);

        @Valid
        User checker = user;

        userRepository.save(user);
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

        if(!user.getOwnedBooks().contains(book))
            throw new IllegalStateException("User doesn't have that book");
        book.setAvailableQuantity(book.getAvailableQuantity() + 1);

        user.getOwnedBooks().remove(book);
    }

    public List<Book> getRecommendation(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("User not found")
                );

        List<Book> userBooks = user.getOwnedBooks();

        if(userBooks.isEmpty())
            throw new IllegalStateException("User doesn't have any books");

        String mostCommonGenre = getMostCommonGenre(userBooks);

        List<Book> genreBooks = bookRepository.findByGenre(mostCommonGenre)
                .orElseThrow(
                        () -> new IllegalStateException("")
                );

        return genreBooks;
    }

    private String getMostCommonGenre(List<Book> userBooks) {
        HashMap<String, Integer> freqMap = new HashMap<String, Integer>();

        for(Book book : userBooks) {
            String temp = book.getGenre();

            if(freqMap.containsKey(temp))
                freqMap.put(temp, freqMap.get(temp)+1);
            else
                freqMap.put(temp, 1);
        }

        PriorityQueue<String> maxPriorityHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxPriorityHeap.addAll(freqMap.keySet());

        return maxPriorityHeap.poll();
    }
}
