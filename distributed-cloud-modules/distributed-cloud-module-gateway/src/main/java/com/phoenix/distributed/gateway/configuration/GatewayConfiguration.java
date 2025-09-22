package com.phoenix.distributed.gateway.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.distributed.gateway.filter.PigRequestGlobalFilter;
import com.phoenix.distributed.gateway.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * 网关配置类
 */
@Configuration(proxyBeanMethods = false)
public class GatewayConfiguration {

    /**
     * 创建PigRequest全局过滤器
     * @return PigRequest全局过滤器
     */
    @Bean
    public PigRequestGlobalFilter pigRequestGlobalFilter() {
        return new PigRequestGlobalFilter();
    }

    /**
     * 创建全局异常处理程序
     * @param objectMapper 对象映射器
     * @return 全局异常处理程序
     */
    @Bean
    public GlobalExceptionHandler globalExceptionHandler(ObjectMapper objectMapper) {
        return new GlobalExceptionHandler(objectMapper);
    }

}
