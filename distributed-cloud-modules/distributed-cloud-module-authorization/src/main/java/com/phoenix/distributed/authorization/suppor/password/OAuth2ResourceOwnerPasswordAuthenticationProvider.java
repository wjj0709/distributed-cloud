package com.phoenix.distributed.authorization.suppor.password;

import com.phoenix.distributed.authorization.suppor.base.OAuth2ResourceOwnerBaseAuthenticationProvider;
import com.phoenix.distributed.common.constant.SecurityConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;

import java.util.Map;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * OAuth2 资源所有者密码认证提供者
 */
public class OAuth2ResourceOwnerPasswordAuthenticationProvider extends OAuth2ResourceOwnerBaseAuthenticationProvider<OAuth2ResourceOwnerPasswordAuthenticationToken> {

    private static final Logger LOGGER = LogManager.getLogger(OAuth2ResourceOwnerPasswordAuthenticationProvider.class);

    /**
     * 使用提供的参数构造一个OAuth2ResourceOwnerPasswordAuthenticationProvider
     * @param authenticationManager 认证管理器
     * @param authorizationService 授权服务
     * @param tokenGenerator 令牌生成器
     * @since 0.2.3
     */
    public OAuth2ResourceOwnerPasswordAuthenticationProvider(AuthenticationManager authenticationManager,
                                                             OAuth2AuthorizationService authorizationService,
                                                             OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator) {
        super(authenticationManager, authorizationService, tokenGenerator);
    }

    /**
     * 构建用户名密码认证令牌
     * @param reqParameters 请求参数映射，包含用户名和密码
     * @return 用户名密码认证令牌
     */
    @Override
    public UsernamePasswordAuthenticationToken buildToken(Map<String, Object> reqParameters) {
        String username = (String) reqParameters.get(OAuth2ParameterNames.USERNAME);
        String password = (String) reqParameters.get(OAuth2ParameterNames.PASSWORD);
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    /**
     * 判断是否支持指定的认证类型
     * @param authentication 待验证的认证类型
     * @return 如果支持该认证类型则返回true，否则返回false
     */
    @Override
    public boolean supports(Class<?> authentication) {
        boolean supports = OAuth2ResourceOwnerPasswordAuthenticationToken.class.isAssignableFrom(authentication);
        LOGGER.debug("supports authentication=" + authentication + " returning " + supports);
        return supports;
    }

    /**
     * 检查客户端是否支持密码授权模式
     * @param registeredClient 已注册的客户端
     * @throws OAuth2AuthenticationException 当客户端不支持密码授权模式时抛出异常
     */
    @Override
    public void checkClient(RegisteredClient registeredClient) {
        assert registeredClient != null;
        if (!registeredClient.getAuthorizationGrantTypes().contains(new AuthorizationGrantType(SecurityConstants.PASSWORD))) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.UNAUTHORIZED_CLIENT);
        }
    }
}
