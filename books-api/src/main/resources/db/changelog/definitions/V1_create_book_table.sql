--liquibase formatted sql
--changeset Mani:create-tables
CREATE SCHEMA IF NOT EXISTS book_store;
CREATE TABLE book_store.books(
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(1000),
                         published_date DATE,
                         total_copies INT,
                         issued_copies INT,
                         available_copies INT,
                         author VARCHAR(50)
);