--liquibase formatted sql
--changeset Mani:inset-books-test-data
INSERT INTO issuers_store.customer_books(customer_user_name, customer_name, book_id, quantity, requested_type)
VALUES('suraj', 'Suraj Krishna', 1, 1, 'buy');
INSERT INTO issuers_store.customer_books(customer_user_name, customer_name, book_id, quantity, requested_type)
VALUES('niraj', 'Niraj Krishna', 2, 1, 'buy');
INSERT INTO issuers_store.customer_books(customer_user_name, customer_name, book_id, quantity, requested_type)
VALUES('vihan', 'Vihan Krishna', 3, 1, 'buy');
INSERT INTO issuers_store.customer_books(customer_user_name, customer_name, book_id, quantity, requested_type)
VALUES('nikhil', 'nikhil Krishna', 4, 1, 'buy');