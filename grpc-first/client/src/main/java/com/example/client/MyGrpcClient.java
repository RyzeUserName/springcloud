package com.example.client;

import com.example.apifirst.HelloRequest;
import com.example.apifirst.HelloResponse;
import com.example.apifirst.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-06 11:12
 */
public class MyGrpcClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
            .usePlaintext()
            .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub =
            HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(
            HelloRequest.newBuilder()
                .setName("小明")
                .setAge(17)
                .addHobbies("唱跳、Rap、篮球").putTags("how?", "wonderful")
                .build());

        System.out.println(helloResponse);

        channel.shutdown();
    }
}
