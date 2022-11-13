package com.amazingbooks.services.books.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "books", schema = "book_store")
@AllArgsConstructor
@EqualsAndHashCode
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column
    private Date publishedDate;

    @Column
    private int totalCopies;

    @Column
    private int issuedCopies;

    @Column
    private int availableCopies;

    @Column
    private String author;
}
