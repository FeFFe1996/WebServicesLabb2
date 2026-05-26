package com.example.userservice.service;

import com.example.userservice.Exceptions.EmailAlreadyExistsException;
import com.example.userservice.Exceptions.UserAlreadyExistsException;
import com.example.userservice.dto.RegisterRequestDto;
import com.example.userservice.entity.UserClient;
import com.example.userservice.repository.UserClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final UserClientRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(UserClientRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerUser(RegisterRequestDto registerRequest){
        System.out.println("incoming username" + registerRequest.username());
        UserClient user = new UserClient();

        user.setUsername(registerRequest.username());
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        try{
            repository.save(user);
        } catch (DataIntegrityViolationException de){
            String message = de.getMessage() != null ? de.getMessage().toLowerCase() : "";
            if (message.contains("username")) {
                throw new UserAlreadyExistsException(registerRequest.username(), de);
            }
        }
    }
}
