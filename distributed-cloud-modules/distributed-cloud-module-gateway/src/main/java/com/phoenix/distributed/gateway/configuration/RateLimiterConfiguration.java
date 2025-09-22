package com.phoenix.distributed.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * 路由限流配置类
 */
@Configuration(proxyBeanMethods = false)
public class RateLimiterConfiguration {

    /**
     * 创建基于远程地址的KeyResolver实例
     * @return 根据请求的远程地址生成限流key的KeyResolver
     * @see <a href=
     * "https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/#the-requestratelimiter-gatewayfilter-factory">Spring
     * Cloud Gateway文档</a>
     */
    @Bean
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono
                .just(Objects.requireNonNull(Objects.requireNonNull(exchange.getRequest().getRemoteAddress()))
                        .getAddress()
                        .getHostAddress());
    }

}
