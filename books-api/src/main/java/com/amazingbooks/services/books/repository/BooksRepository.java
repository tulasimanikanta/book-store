package com.amazingbooks.services.books.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.amazingbooks.services.books.bean.Book;


public interface BooksRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    Optional<Book> findById(Long studentId);
    Book save(Book student);
    void delete(Book student);
    void deleteById(Long studentId);
}
