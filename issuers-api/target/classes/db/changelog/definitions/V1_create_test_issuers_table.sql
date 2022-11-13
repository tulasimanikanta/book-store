--liquibase formatted sql
--changeset Mani:create-tables

CREATE SCHEMA IF NOT EXISTS issuers_store;
CREATE TABLE issuers_store.customer_books(
                         id SERIAL PRIMARY KEY,
                         customer_user_name VARCHAR(100),
                         customer_name VARCHAR(100),
                         book_id INT,
                         quantity INT,
                         requested_type varchar(10),
                         created_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);