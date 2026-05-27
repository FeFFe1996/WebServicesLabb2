package com.example.messageservice.exceptions;

public class NoTopicException extends RuntimeException {
    public NoTopicException(String message) {
        super(message);
    }
}
