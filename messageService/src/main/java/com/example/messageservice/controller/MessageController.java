package com.example.messageservice.controller;

import com.example.messageservice.service.MessagesService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private MessagesService messagesService;

    public MessageController(MessagesService messagesService){
        this.messagesService = messagesService;
    }
}
