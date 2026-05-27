package com.example.messageservice.dto;

public record CreateTopicDto(
        String topicName,
        String createdBy,
        String description
) {
}
