package com.example.messageservice.dto;

public record GetTopicTitlesDto(
        Long id,
        String topicName,
        String createdAt,
        String createdBy
) {
}
