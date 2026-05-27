package com.example.messageservice.dto;

import com.example.messageservice.entity.MessageTopic;

public record CreateMessageDto(
        MessageTopic topic,
        String username,
        String message
) {
}
