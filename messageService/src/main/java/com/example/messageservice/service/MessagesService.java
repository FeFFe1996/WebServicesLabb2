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
import org.example.grpc.CreateRequest;
import org.example.grpc.UserInfo;
import org.example.grpc.UserServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;
    private final MessageTopicRepository messageTopicRepository;
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public MessagesService(
            MessagesRepository messagesRepository,
            MessageTopicRepository messageTopicRepository,
            UserServiceGrpc.UserServiceBlockingStub stub){
        this.messagesRepository = messagesRepository;
        this.messageTopicRepository = messageTopicRepository;
        this.userServiceStub = stub;
    }

    @Transactional
    public void createTopic(CreateTopicDto createTopicDto){
        CreateRequest request = CreateRequest.newBuilder()
                .setUserId(createTopicDto.userId())
                .build();

        UserInfo infoResponse = userServiceStub.sendUserInfo(request);
        String userName = infoResponse.getUsername();

        MessageTopic topic = new MessageTopic();
        topic.setTopicName(createTopicDto.topicName());
        topic.setCreatedBy(userName);
        topic.setDescription(createTopicDto.description());
        topic.setMesssagesList(new ArrayList<>());

        messageTopicRepository.save(topic);
    }

    @Transactional
    public void createMessage(CreateMessageDto createMessageDto){
        MessageTopic topic = messageTopicRepository.findById(createMessageDto.topicId())
                .orElseThrow(() -> new NoTopicWithThatIdException(
                        "Cannot create message. Topic with id: " + createMessageDto.topicId() + " does not exist!"));

        CreateRequest request = CreateRequest.newBuilder()
                .setUserId(createMessageDto.userId())
                .build();

        UserInfo infoResponse = userServiceStub.sendUserInfo(request);
        String userName = infoResponse.getUsername();

        Messages messages = new Messages();
        messages.setUsername(userName);
        messages.setMessage(createMessageDto.message());
        messages.setTopic(topic);

        messagesRepository.save(messages);
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
