--liquibase formatted sql
--changeset Mani:inset-books-test-data
INSERT INTO book_store.books(title, published_date, total_copies, issued_copies, available_copies, author)
VALUES('Java Book', '10/10/2022', 10, 5, 5, 'Book author1');
INSERT INTO book_store.books(title, published_date, total_copies, issued_copies, available_copies, author)
VALUES('DotNet Book', '10/10/2022', 10, 10, 0, 'Book author2');
INSERT INTO book_store.books(title, published_date, total_copies, issued_copies, available_copies, author)
VALUES('Angular Book', '10/10/2022', 10, 0, 10, 'Book author3');
INSERT INTO book_store.books(title, published_date, total_copies, issued_copies, available_copies, author)
VALUES('React Book', '10/10/2022', 10, 2, 8, 'Book author4');