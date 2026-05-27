package com.example.messageservice.exceptions;

public class NoTopicWithThatIdException extends RuntimeException {
    public NoTopicWithThatIdException(String message) {
        super(message);
    }
}
