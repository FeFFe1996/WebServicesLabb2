package com.example.bff.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;

@Service
public class Oauth2JwtService {
    private final OAuth2AuthorizedClientService clientService;

    public Oauth2JwtService(OAuth2AuthorizedClientService clientService){
        this.clientService = clientService;
    }

    public String getAccessToken(Authentication authentication){
        if (authentication == null)
            return null;

        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                "authservice",
                authentication.getName()
        );

        return (client != null) ? client.getAccessToken().getTokenValue() : null;
    }
}
