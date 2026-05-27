package com.example.messageservice.repositories;

import com.example.messageservice.entity.MessageTopic;
import org.springframework.data.repository.ListCrudRepository;


public interface MessageTopicRepository extends ListCrudRepository<MessageTopic, Long> {
    MessageTopic findMessageTopicById(long id);
}
