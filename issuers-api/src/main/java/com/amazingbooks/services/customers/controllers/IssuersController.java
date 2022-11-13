package com.amazingbooks.services.customers.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazingbooks.services.customers.bean.Book;
import com.amazingbooks.services.customers.bean.CustomerBook;
import com.amazingbooks.services.customers.exception.RoleIssueException;
import com.amazingbooks.services.customers.service.IssuerService;

@RestController
public class IssuersController {
    private final IssuerService issuerService;

    public IssuersController(IssuerService issuerService) {
        this.issuerService = issuerService;
    }

    @GetMapping("customers/books/available")
    public List<Book> findAvailableBooks() {
        return issuerService.findAvailableBooks();
    }

    @GetMapping("customers")
    public List<CustomerBook> findCustomer(Authentication authentication) {
        String userName = authentication.getName();
        return issuerService.findCustomerBooks(userName);
    }

    @GetMapping("admin/customers")
    public List<CustomerBook> findAllCustomers(Authentication authentication) {
        checkAccess(authentication);
        return issuerService.getAllCustomers();
    }

    @GetMapping("admin/customers/{userName}")
    public List<CustomerBook> findAllCustomers(@PathVariable("userName") String userName,
                                               Authentication authentication) {
        checkAccess(authentication);
        return issuerService.findCustomerBooks(userName);
    }

    @PostMapping("customers/buy")
    public ResponseEntity<String> buyBooks(@RequestBody CustomerBook customerBook, Authentication authentication) {
        String userName = authentication.getName();
        customerBook.setCustomerUserName(userName);
        issuerService.buyBooks(customerBook);
        return ResponseEntity.accepted().body("OK");
    }

    @PostMapping("customers/return")
    public ResponseEntity<String> returnBooks(@RequestBody CustomerBook customerBook, Authentication authentication) {
        String userName = authentication.getName();
        customerBook.setCustomerUserName(userName);
        issuerService.returnBooks(customerBook);
        return ResponseEntity.accepted().body("OK");
    }

    private void checkAccess(Authentication authentication) {
        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
        if (!hasUserRole) {
            throw new RoleIssueException("User " + " cannot access this resource");
        }
    }
}
