package com.amazingbooks.services.oauth.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    List<String> users = List.of("suraj", "niraj", "vihan", "nikhil");
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> testUsers = new ArrayList<>();
        users.forEach(testUser -> {
                        UserDetails user = User.withDefaultPasswordEncoder()
                                .username(testUser)
                                .password("default")
                                .roles("USER")
                                .build();
                        testUsers.add(user);
                        });

        UserDetails adminUser = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        testUsers.add(adminUser);
        return new InMemoryUserDetailsManager(testUsers);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
