package com.example.messageservice.controller;

import com.example.messageservice.service.MessagesService;
import org.example.grpc.UserServiceGrpc;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    private MessagesService messagesService;
    final UserServiceGrpc.UserServiceBlockingStub stub;

    public MessageController(MessagesService messagesService, UserServiceGrpc.UserServiceBlockingStub stub){
        this.messagesService = messagesService;
        this.stub = stub;
    }

    @GetMapping("/userinfo")
    public String getInfo(@RequestHeader(value = "X-User-Name", defaultValue = "anonymous") String username){
        return null;
    }

}
