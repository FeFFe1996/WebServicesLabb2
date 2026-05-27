package com.example.messageservice.exceptions;

public class NoTopicWithThatNameExistsException extends RuntimeException {
    public NoTopicWithThatNameExistsException(String message) {
        super(message);
    }
}
