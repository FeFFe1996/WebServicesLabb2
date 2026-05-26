package com.example.authorizationservice.service;

import com.example.authorizationservice.dto.AuthUserDto;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class RestClientUserDetailsService implements UserDetailsService {

    private final RestClient restClient;

    public RestClientUserDetailsService(RestClient restClient){
        this.restClient = restClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            AuthUserDto userDto = restClient
                    .get()
                    .uri("/users/{username}", username)
                    .retrieve()
                    .body(AuthUserDto.class);
            if (userDto == null){
                throw new UsernameNotFoundException("user not found with name: " + username);
            }

            return User.withUsername(userDto.username())
                    .password(userDto.password())
                    .authorities(userDto.roles().toArray(new String[0]))
                    .build();
        }catch (HttpClientErrorException.NotFound e){
            throw new UsernameNotFoundException("User not found: " + username);
        } catch (Exception e){
            throw new AuthenticationServiceException("User Service unavailable: " + e);
        }
    }
}
