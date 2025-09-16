package com.phoenix.distributed.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthorizationDistributedApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationDistributedApplication.class, args);
    }
}
