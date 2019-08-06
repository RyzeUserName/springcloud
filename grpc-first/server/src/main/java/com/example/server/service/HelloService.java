package com.example.server.service;

import com.example.apifirst.HelloRequest;
import com.example.apifirst.HelloResponse;
import com.example.apifirst.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * 实现
 * @author Ryze
 * @date 2019-08-06 11:09
 */
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {
    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println( request );
        String greeting = "Hi " + request.getName() + " you are " + request.getAge() + " years old" +
            " your hoby is " + (request.getHobbiesList()) + " your tags " + request.getTagsMap();
        HelloResponse response = HelloResponse.newBuilder().setGreeting( greeting ).build();
        responseObserver.onNext( response );
        responseObserver.onCompleted();
    }
}
