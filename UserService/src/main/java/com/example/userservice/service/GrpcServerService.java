package com.example.userservice.service;

import com.example.userservice.entity.UserClient;
import com.example.userservice.repository.UserClientRepository;
import io.grpc.stub.StreamObserver;
import org.example.grpc.CreateRequest;
import org.example.grpc.UserInfo;
import org.example.grpc.UserServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServerService extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    private UserClientRepository clientRepository;

    @Override
    public void sendUserInfo(CreateRequest createReq, StreamObserver<UserInfo> responseObserver){
        String userId = createReq.getUserId();

        String userName = clientRepository.findById(Long.getLong(userId))
                .map(UserClient::getUsername)
                .orElse("Unknown user");

        UserInfo info = UserInfo.newBuilder()
                .setUsername(userName)
                .build();

        responseObserver.onNext(info);
        responseObserver.onCompleted();
    }
}
