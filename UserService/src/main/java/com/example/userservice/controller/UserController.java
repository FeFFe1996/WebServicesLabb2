package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserClientRepository;
import com.example.userservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ClientService service;
    private final UserClientRepository repository;

    public UserController(ClientService service, UserClientRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String username){
        return repository.findByUsername(username)
                .map(user -> ResponseEntity.ok(new UserDto(
                        user.username(),
                        user.password(),
                        List.of("USER_ROLE")
                ))).orElse(ResponseEntity.notFound().build());
    }
}
