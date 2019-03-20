package com.lft.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        //生成
        //keytool -genkeypair -alias server -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore server.p12 -validity 3650
        //密码:Spring Cloud
        //keytool -genkeypair -alias client -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore client.p12 -validity 3650
        //密码:Client

        //导出
        //keytool -export -alias server -file server.crt --keystore server.p12
        //keytool -export -alias client -file client.crt --keystore client.p12

        //将server.crt 导入 client.p12 输入 client 密码
        //keytool -import -alias server -file server.crt --keystore client.p12

        //将client.crt 导入 server.p12 输入 server 密码
        //keytool -import -alias client -file client.crt --keystore server.p12

    }

}
