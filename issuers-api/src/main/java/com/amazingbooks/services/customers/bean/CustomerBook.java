package com.amazingbooks.services.customers.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name = "customer_books", schema = "issuers_store")
@AllArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerBook {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;

    @Column
    private String customerUserName;

    @Column
    private String customerName;

    @Column
    private Long bookId;

    @Column
    private String requestedType;

    @Column
    private int quantity;
}