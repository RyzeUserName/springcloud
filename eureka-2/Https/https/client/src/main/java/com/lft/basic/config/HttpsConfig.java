package com.lft.basic.config;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * 描述
 * @author Ryze
 * @date 2019-03-13 18:42
 */
@Profile({"https"})
@Configuration
public class HttpsConfig {

    @Value("${eureka.client.ssl.key-store}")
    private String keyStoreFileName;
    @Value("${eureka.client.ssl.key-store-password}")
    private String keyStorePassword;

    @Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder = new EurekaJerseyClientImpl.EurekaJerseyClientBuilder();
        builder.withClientName("eureka-https-client");
        SSLContext sslContext = new SSLContextBuilder()
            .loadTrustMaterial(
                this.getClass().getClassLoader().getResource(keyStoreFileName),keyStorePassword.toCharArray()
            )
            .build();
        builder.withCustomSSL(sslContext);
        builder.withMaxTotalConnections(10);
        builder.withMaxConnectionsPerHost(10);
        DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
        args.setEurekaJerseyClient(builder.build());
        return args;
    }
}
