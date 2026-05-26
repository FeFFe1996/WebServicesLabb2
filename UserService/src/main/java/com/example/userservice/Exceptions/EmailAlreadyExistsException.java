package com.example.userservice.Exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    private final String email;

    public EmailAlreadyExistsException(String email) {
        super("Email already exists");
        this.email=email;
    }

    public EmailAlreadyExistsException(String email, Throwable cause){
        super("Email already exists", cause);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
