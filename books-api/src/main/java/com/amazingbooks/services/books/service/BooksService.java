package com.amazingbooks.services.books.service;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.amazingbooks.services.books.bean.Book;
import com.amazingbooks.services.books.exception.BookUnavailable;
import com.amazingbooks.services.books.exception.UnknownBookException;
import com.amazingbooks.services.books.repository.BooksRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BooksService {
    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findBookById(Long bookId) {
        log.trace("finding the book details by {}", bookId);
        if (bookId == null || bookId < 0) {
            throw new UnknownBookException("Provide correct book Id");
        }
        var book = booksRepository.findById(bookId);
        return book.orElseThrow(() -> {
            throw new UnknownBookException("Book is not available");
        });
    }

    public void updateBook(Long bookId, Book book) {
        log.trace("Updating the book {} details {}", bookId, book);
        var existingBook = booksRepository.findById(bookId);
        if (existingBook.isPresent()) {
            book.setId(bookId);
            booksRepository.save(book);
        } else {
            booksRepository.save(book);
        }
    }

    public void buyBook(Long bookId, int quantity) {
        log.trace("buying the book details by {}", bookId);
        var existingBook = this.findBookById(bookId);
        int availableCopies = existingBook.getAvailableCopies();
        if (availableCopies > quantity) {
            int remainingAvailable = availableCopies - quantity;
            int issuedBooks = existingBook.getIssuedCopies() + quantity;
            existingBook.setAvailableCopies(remainingAvailable);
            existingBook.setIssuedCopies(issuedBooks);
        } else {
            throw new BookUnavailable("exceeds available " + + existingBook.getAvailableCopies() +
                    " ,please try again with available quantity" );
        }
        log.debug("updating book quantity {}", bookId);
        booksRepository.save(existingBook);
    }

    public void returnBook(Long bookId, int returnQuantity) {
        log.trace("rerun the book details by {}", bookId);
        var existingBook = this.findBookById(bookId);
        int availableCopies = existingBook.getAvailableCopies();
        int totalCopies = existingBook.getTotalCopies();
        int issuedCopies = existingBook.getIssuedCopies();
        int returnedTotalBooks = availableCopies + returnQuantity;
        if (totalCopies > returnedTotalBooks) {
            existingBook.setAvailableCopies(returnedTotalBooks);
            existingBook.setIssuedCopies(issuedCopies + returnQuantity);
        } else {
            throw new BookUnavailable("exceeds available " +  existingBook.getAvailableCopies() +
                    " ,please try again with available returnQuantity");
        }
        log.debug("updating book returnQuantity {}", bookId);
        booksRepository.save(existingBook);
    }

    public Book saveBook(Book book) {
        return booksRepository.save(book);
    }

    public void deleteById(Long bookId) {
        if (bookId == null) {
            throw new UnknownBookException("Provide correct book Id");
        }
        try {
            booksRepository.deleteById(bookId);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            throw new UnknownBookException("Book is not available");
        }
    }
}
