package com.phoenix.distributed.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDistributedApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayDistributedApplication.class, args);
    }
}
