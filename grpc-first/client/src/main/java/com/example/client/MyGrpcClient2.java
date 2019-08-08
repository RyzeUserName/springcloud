package com.example.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-06 11:12
 */
public class MyGrpcClient2 {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();

        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);

        HelloReply helloResponse = stub.sayHello(
            HelloRequest.newBuilder().setName("小明").build());

        System.out.println(helloResponse.getMessage());

        channel.shutdown();
    }
}
