package com.example.messageservice.service;

import io.grpc.stub.StreamObserver;
import org.example.grpc.CreateRequest;
import org.example.grpc.UserInfo;
import org.example.grpc.UserServiceGrpc;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServerService extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void sendUserInfo(CreateRequest createReq, StreamObserver<UserInfo> responseObserver){
        String name = createReq.getUsername();

        UserInfo info = UserInfo.newBuilder()
                .setUsername(name)
                .build();

        responseObserver.onNext(info);
        responseObserver.onCompleted();
    }
}
