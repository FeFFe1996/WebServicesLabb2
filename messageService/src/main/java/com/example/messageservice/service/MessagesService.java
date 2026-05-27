package com.example.messageservice.service;

import com.example.messageservice.dto.*;
import com.example.messageservice.entity.MessageTopic;
import com.example.messageservice.entity.Messages;
import com.example.messageservice.exceptions.NoTopicException;
import com.example.messageservice.exceptions.NoTopicWithThatIdException;
import com.example.messageservice.mapper.MessageMapper;
import com.example.messageservice.repositories.MessageTopicRepository;
import com.example.messageservice.repositories.MessagesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final MessageTopicRepository messageTopicRepository;


    public MessagesService(MessagesRepository messagesRepository, MessageTopicRepository messageTopicRepository){
        this.messagesRepository = messagesRepository;
        this.messageTopicRepository = messageTopicRepository;

    }

    @Transactional
    public void createTopic(CreateTopicDto createTopicDto){
        MessageTopic topic = new MessageTopic();

        topic.setTopicName(createTopicDto.topicName());
        topic.setCreatedBy(createTopicDto.createdBy());
        topic.setDescription(createTopicDto.description());
        topic.setMesssagesList(new ArrayList<>());

        messageTopicRepository.save(topic);
    }

    @Transactional
    public void createMessage(CreateMessageDto createMessageDto){
        Messages messages = new Messages();
        messages.setUsername(createMessageDto.username());
        messages.setMessage(createMessageDto.message());

        try {
            MessageTopic topic = messageTopicRepository.findMessageTopicById(createMessageDto.topic().getId());
            messages.setTopic(topic);
            messagesRepository.save(messages);
        }catch (EntityNotFoundException e){
            throw new NoTopicWithThatIdException("Cannot create message. Topic with id: " + createMessageDto.topic().getId() + " does not exist!" );
        }
    }

    public List<GetTopicTitlesDto> getTopicTitles(){
        List<MessageTopic> topics = messageTopicRepository.findAll();

        if (topics.isEmpty()){
            throw new NoTopicException("No topics is found, create a new one to add to list");
        }

        return topics.stream()
                .map(MessageMapper::toTopicTitleDto)
                .toList();
    }

    public GetTopicDto getTopicById(long id){
        MessageTopic messageTopic = messageTopicRepository.findById(id)
                .orElseThrow(() -> new NoTopicWithThatIdException("Topic with id: " + id + " not found"));

        return MessageMapper.toTopicDto(messageTopic);
    }

    public List<GetMessagesDto> getMessagesFromTopicId(Long id){
        MessageTopic topic = messageTopicRepository.findById(id)
                .orElseThrow(() -> new NoTopicWithThatIdException("Topic with id: " + id + " not found"));

        return messagesRepository.findMessagesByTopic(topic);
    }
}
