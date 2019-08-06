package com.example.grpcclient.service;

import com.exemple.api.HelloReply;
import com.exemple.api.HelloRequest;
import com.exemple.api.SimpleGrpc;
import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;

/**
 * 描述
 * @author Ryze
 * @date 2019-08-06 16:11
 */
@Service
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
    @GrpcClient("grpc-server")
    private Channel channel;

    public String sendMessage(String name) {
        SimpleGrpc.SimpleBlockingStub stub = SimpleGrpc.newBlockingStub(channel);
        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName(name).build());
        return response.getMessage();
    }
}