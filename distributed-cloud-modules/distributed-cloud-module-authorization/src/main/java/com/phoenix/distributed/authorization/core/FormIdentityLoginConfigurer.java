package com.phoenix.distributed.authorization.core;

import com.phoenix.distributed.authorization.handler.FormAuthenticationFailureHandler;
import com.phoenix.distributed.authorization.handler.SsoLogoutSuccessHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * 基于授权码模式的统一认证登录配置类，适用于Spring Security和SAS
 */
public final class FormIdentityLoginConfigurer
        extends AbstractHttpConfigurer<FormIdentityLoginConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.formLogin(formLogin -> {
                    formLogin.loginPage("/token/login");
                    formLogin.loginProcessingUrl("/oauth2/form");
                    formLogin.failureHandler(new FormAuthenticationFailureHandler());

                })
                .logout(logout -> logout.logoutUrl("/oauth2/logout")
                        .logoutSuccessHandler(new SsoLogoutSuccessHandler())
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)) // SSO登出成功处理

                .csrf(AbstractHttpConfigurer::disable);
    }
}
