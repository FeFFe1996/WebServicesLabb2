package com.example.messageservice.dto;

import com.example.messageservice.entity.MessageTopic;

public record CreateMessageDto(
        Long topicId,
        String userId,
        String message
) {
}
