package com.amazingbooks.services.books.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
public class SecurityConfiguration {
    @Value("${spring.security.oauth.check-token-uri}")
    private String checkTokenUri;
    @Value("${spring.security.oauth.client-id}")
    private String clientId;
    @Value("${spring.security.oauth.client-secret}")
    private String clientSecret;

    @Primary
    @Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(checkTokenUri);
        tokenService.setClientId(clientId);
        tokenService.setClientSecret(clientSecret);
        return tokenService;
    }
}
