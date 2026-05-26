package com.example.userservice.controller;

import com.example.userservice.Exceptions.UserAlreadyExistsException;
import com.example.userservice.dto.RegisterRequestDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.repository.UserClientRepository;
import com.example.userservice.service.ClientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final ClientService service;
    private final UserClientRepository repository;

    public UserController(ClientService service, UserClientRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserByName(@PathVariable String username){
        var userT = repository.findByUsername(username);
        System.out.println(userT.get().username());

        return repository.findByUsername(username)
                .map(user -> ResponseEntity.ok(new UserDto(
                        user.username(),
                        user.password(),
                        List.of("USER_ROLE")
                ))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        try {
            service.registerUser(registerRequestDto);

            HttpHeaders headers = new org.springframework.http.HttpHeaders();
            headers.add("HX-Redirect", "/");

            return new ResponseEntity<>(
                    "<div class='message success'>Registration successful! Redirecting...</div>",
                    headers,
                    HttpStatus.OK
            );
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("<div class='message error'>An account with that username already exists.</div>");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("<div class='message error'>Registration failed. Please try again later.</div>");
        }
    }
}
