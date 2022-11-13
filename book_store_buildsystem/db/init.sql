create database books;
create database issuers;
\connect books;
CREATE SCHEMA IF NOT EXISTS book_store;
\connect issuers;
CREATE SCHEMA IF NOT EXISTS issuers_store;