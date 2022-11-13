package com.amazingbooks.services.customers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.amazingbooks.services.customers.bean.CustomerBook;


public interface IssuersRepository extends CrudRepository<CustomerBook, Long> {
    List<CustomerBook> findAll();
    Optional<CustomerBook> findById(Long customerId);
    CustomerBook save(CustomerBook customer);
    void delete(CustomerBook customer);
    void deleteById(Long customerId);
    @Query( value = "select * from issuers_store.customer_books cb where cb.customer_user_name = :userName", nativeQuery = true)
    List<CustomerBook> findByUserName(String userName);
}
