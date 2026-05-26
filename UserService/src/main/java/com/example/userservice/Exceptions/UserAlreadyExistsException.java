package com.example.userservice.Exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    private final String userName;

    public UserAlreadyExistsException(String userName) {
        super("User already exists");
        this.userName = userName;
    }

    public UserAlreadyExistsException(String uName, Throwable cause){
        super("User already exists", cause);
        this.userName = uName;
    }

    public String getUserName() {
        return userName;
    }
}
