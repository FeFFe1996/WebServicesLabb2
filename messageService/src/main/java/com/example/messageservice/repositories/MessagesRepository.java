package com.example.messageservice.repositories;

import com.example.messageservice.dto.GetMessagesDto;
import com.example.messageservice.entity.MessageTopic;
import com.example.messageservice.entity.Messages;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface MessagesRepository extends ListCrudRepository<Messages, Long> {
    List<GetMessagesDto> findMessagesByTopicId(Long topicId);

    List<GetMessagesDto> findMessagesByTopic(MessageTopic topic);
}
