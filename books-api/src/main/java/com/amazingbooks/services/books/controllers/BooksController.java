package com.amazingbooks.services.books.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazingbooks.services.books.bean.Book;
import com.amazingbooks.services.books.service.BooksService;

@RestController
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService bookService) {
        this.booksService = bookService;
    }

    @GetMapping("books")
    public List<Book> getAllBooks() {
        return booksService.findAll();
    }

    @GetMapping("books/{bookId}")
    public Book getBook(@PathVariable("bookId") Long bookId) {
        return booksService.findBookById(bookId);
    }

    @PostMapping("books")
    public Long addBook(@RequestBody Book book) {
        var storedBook = booksService.saveBook(book);
        return storedBook.getId();
    }

    @PutMapping("books/{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        booksService.updateBook(bookId, book);
    }

    @PutMapping("books/{bookId}/buy/{quantity}")
    public void buyBook(@PathVariable("bookId") Long bookId, @PathVariable("quantity") int quantity) {
        booksService.buyBook(bookId, quantity);
    }

    @PutMapping("books/{bookId}/return/{quantity}")
    public void returnBook(@PathVariable("bookId") Long bookId, @PathVariable("quantity") int quantity) {
        booksService.returnBook(bookId, quantity);
    }
}
