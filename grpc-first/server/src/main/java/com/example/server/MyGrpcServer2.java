package com.example.server;

import com.example.server.service.HelloService2;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-06 11:11
 */
public class MyGrpcServer2 {

    static public void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
            .addService(new HelloService2())
            .build();
        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }
}
