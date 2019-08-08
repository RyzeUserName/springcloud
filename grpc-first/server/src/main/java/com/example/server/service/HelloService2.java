package com.example.server.service;

import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-08 11:26
 */
public class HelloService2 extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        System.out.println(request.getName());
        responseObserver.onNext( HelloReply.newBuilder().setMessage("hi! " + request.getName()).build());
        responseObserver.onCompleted();
    }
}
