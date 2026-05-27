package com.example.messageservice.exceptions;

public class MessageUnavailableException extends RuntimeException {
    public MessageUnavailableException(String message) {
        super(message);
    }
}
