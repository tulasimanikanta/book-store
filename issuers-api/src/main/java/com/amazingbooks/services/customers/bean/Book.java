package com.amazingbooks.services.customers.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class Book {
    @JsonProperty("id")
    private Long bookId;
    private String title;
    private Date publishedDate;
    private int totalCopies;
    private int issuedCopies;
    private int availableCopies;
    private String author;
}
