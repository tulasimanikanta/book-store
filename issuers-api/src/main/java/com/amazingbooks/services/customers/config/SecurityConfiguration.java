package com.amazingbooks.services.customers.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SecurityConfiguration {

    @Configuration
    @ConditionalOnProperty(prefix = "security.oauth2.client", value = "grant-type", havingValue = "client_credentials")
    public static class OAuthRestTemplateConfigurer {
        @Bean
        public OAuth2RestTemplate oauth2RestTemplate(OAuth2ProtectedResourceDetails details) {
            OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(details);
            return oAuth2RestTemplate;
        }
    }
}
