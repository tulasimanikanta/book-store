package com.amazingbooks.services.customers.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.amazingbooks.services.customers.bean.Book;
import com.amazingbooks.services.customers.bean.CustomerBook;
import com.amazingbooks.services.customers.exception.BookOutOfStockException;
import com.amazingbooks.services.customers.repository.IssuersRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IssuerService {
    private final IssuersRepository issuersRepository;
    private final OAuth2RestTemplate booksRestTemplate;
    private final DefaultUriBuilderFactory factory;

    @Autowired
    public IssuerService(OAuth2RestTemplate booksRestTemplate,
                         IssuersRepository issuersRepository,
                         @Value("${external-apps.books-api-base-url}") String booksUri ) {
        this.issuersRepository = issuersRepository;
        this.booksRestTemplate = booksRestTemplate;
        factory = new DefaultUriBuilderFactory(booksUri);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);

    }

    public List<Book> findAvailableBooks() {
        String booksUrl = factory.builder().pathSegment("books").build().toString();
        var entity = booksRestTemplate.getForEntity(
                booksUrl,
                Book[].class);
        return Arrays.stream(Objects.requireNonNull(entity.getBody())).collect(Collectors.toList());
    }

    public List<CustomerBook> getAllCustomers() {
       return issuersRepository.findAll();
    }

    public List<CustomerBook> findCustomerBooks(@NonNull String userName) {
        log.trace("finding the customer details by {}", userName);
        var customerBooks = issuersRepository.findByUserName(userName);
        Map<Long, List<CustomerBook>> booksMap = customerBooks.stream().collect(Collectors.groupingBy(CustomerBook::getBookId, Collectors.toList()));
        List<CustomerBook> customerBooks1 = new ArrayList<>();
        booksMap.forEach((key, books) -> {
            int totalBooks = books.stream().map(CustomerBook::getQuantity).reduce(0, Integer::sum);
            books.stream().findFirst().ifPresent(b -> {
                b.setQuantity(totalBooks);
                b.setRequestedType(null);
                customerBooks1.add(b);
            });
        });
        return customerBooks1;
    }

    public void buyBooks(@NonNull CustomerBook customerBook) {
        log.trace("customer {} is buying book {} quantity {}", customerBook.getCustomerUserName(),
                customerBook.getBookId(), customerBook.getQuantity());
        Map<String, Object> bookDetails = new HashMap<>();
        bookDetails.put("bookId", customerBook.getBookId());
        bookDetails.put("quantity", customerBook.getQuantity());

        var booksBuyingBuilder = factory.uriString("/books/{bookId}/buy/{quantity}");
        try {
            booksRestTemplate.put(booksBuyingBuilder.build(bookDetails).toString(), null);
            customerBook.setRequestedType("buy");
            var availableBooks = issuersRepository.findByUserName(customerBook.getCustomerUserName());
            issuersRepository.save(customerBook);
            log.info("customer {} successfully bought book {} quantity {}", customerBook.getCustomerUserName(),
                    customerBook.getBookId(), customerBook.getQuantity());
        } catch (HttpClientErrorException clientErrorException) {
            if (clientErrorException.getStatusCode() == HttpStatus.GONE) {
                log.error("unable to reserve book {}, No Books Available ", customerBook.getBookId(),
                        clientErrorException);
            }
            throw new BookOutOfStockException("Out of Stock!, please try again");
        } catch (Exception exception) {
            log.error("unable to reserve book {} for customer {} ", customerBook.getBookId(),
                    customerBook.getCustomerUserName(), exception);
            throw new BookOutOfStockException("Out of Stock!, please try again");
        }
    }

    public void returnBooks(@NonNull CustomerBook customerBook) {
        log.trace("customer {} is returning book {} quantity {}", customerBook.getCustomerUserName(),
                customerBook.getBookId(), customerBook.getQuantity());
        var bookDetails = Map.of("bookId", customerBook.getBookId(),
                "quantity", customerBook.getQuantity());
        var booksBuyingBuilder = factory.uriString("/books/{bookId}/return/{quantity}");
        try {
            booksRestTemplate.put(booksBuyingBuilder.build(bookDetails).toString(), null);
            customerBook.setRequestedType("return");
            var availableBooks = issuersRepository.findByUserName(customerBook.getCustomerUserName());
            issuersRepository.save(customerBook);
            log.info("customer {} successfully returned book {} quantity {}", customerBook.getCustomerUserName(),
                    customerBook.getBookId(), customerBook.getQuantity());
        } catch (Exception exception) {
            log.error("unable to reserve book {} for customer {} ", customerBook.getBookId(),
                    customerBook.getCustomerUserName(), exception);
            throw new BookOutOfStockException("Out of Stock!, please try again");
        }
    }

}
