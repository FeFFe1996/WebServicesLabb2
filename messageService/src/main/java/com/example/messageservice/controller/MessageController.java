package com.example.messageservice.controller;

import com.example.messageservice.service.MessagesService;
import org.example.grpc.CreateRequest;
import org.example.grpc.UserInfo;
import org.example.grpc.UserServiceGrpc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    private MessagesService messagesService;
    final UserServiceGrpc.UserServiceBlockingStub stub;

    public MessageController(MessagesService messagesService, UserServiceGrpc.UserServiceBlockingStub stub){
        this.messagesService = messagesService;
        this.stub = stub;
    }

    @GetMapping("/userinfo/{username}")
    public String getInfo(@RequestParam(defaultValue = "anonymous") String name){
        CreateRequest request = CreateRequest.newBuilder()
                .setUsername(name)
                .build();
        UserInfo info = stub.sendUserInfo(request);

        return info.getUsername();
    }

}
