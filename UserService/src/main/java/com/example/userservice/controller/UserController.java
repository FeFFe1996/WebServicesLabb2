package com.example.userservice.controller;

import com.example.userservice.repository.UserClientRepository;
import com.example.userservice.service.ClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ClientService service;
    private final UserClientRepository repository;

    public UserController(ClientService service, UserClientRepository repository) {
        this.service = service;
        this.repository = repository;
    }
}
