package com.example.authorizationservice.service;

import com.example.authorizationservice.dto.AuthUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class RestClientDetailsService implements UserDetailsService {

    private final RestClient restClient;

    public RestClientDetailsService() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AuthUserDto userDto = restClient.get()
                    .uri("/api/users/{username}", username)
                    .retrieve()
                    .body(AuthUserDto.class);

            if (userDto == null || userDto.username() == null) {
                throw new UsernameNotFoundException("User payload empty for: " + username);
            }

            return User.builder()
                    .username(userDto.username())
                    .password(userDto.password())
                    .authorities(userDto.roles().toArray(new String[0]))
                    .build();

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new UsernameNotFoundException("User not found in system database: " + username);
            }
            throw new RuntimeException("Inter-service request failure to User Microservice", e);
        }
    }
}
