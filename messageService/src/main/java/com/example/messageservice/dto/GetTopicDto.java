package com.example.messageservice.dto;

import com.example.messageservice.entity.Messages;

import java.util.List;

public record GetTopicDto(
        Long id,
        String topicName,
        String createdAt,
        String createdBy,
        String description,
        List<Messages> messagesList
) {
}
