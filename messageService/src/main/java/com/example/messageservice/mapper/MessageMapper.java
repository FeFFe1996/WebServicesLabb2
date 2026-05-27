package com.example.messageservice.mapper;

import com.example.messageservice.dto.GetTopicTitlesDto;
import com.example.messageservice.dto.GetTopicDto;
import com.example.messageservice.entity.MessageTopic;

public class MessageMapper {
    private MessageMapper(){
    }

    public static GetTopicTitlesDto toTopicTitleDto(MessageTopic messageTopic){
        if (messageTopic == null) return null;

        return new GetTopicTitlesDto(
                messageTopic.getId(),
                messageTopic.getTopicName(),
                messageTopic.getCreatedAt(),
                messageTopic.getCreatedBy());
    }

    public static GetTopicDto toTopicDto(MessageTopic messageTopic){
        if (messageTopic == null) return null;
        return new GetTopicDto(
                messageTopic.getId(),
                messageTopic.getTopicName(),
                messageTopic.getCreatedAt(),
                messageTopic.getCreatedBy(),
                messageTopic.getDescription(),
                messageTopic.getMesssagesList());
    }
}
