package com.example.messageservice.dto;

public record CreateTopicDto(
        String topicName,
        String userId,
        String description
) {
}
