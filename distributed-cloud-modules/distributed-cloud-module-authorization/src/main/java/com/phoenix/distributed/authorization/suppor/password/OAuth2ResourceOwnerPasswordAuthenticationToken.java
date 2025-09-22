package com.phoenix.distributed.authorization.suppor.password;

import com.phoenix.distributed.authorization.suppor.base.OAuth2ResourceOwnerBaseAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

import java.io.Serial;
import java.util.Map;
import java.util.Set;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * OAuth2资源所有者密码认证令牌
 */
public class OAuth2ResourceOwnerPasswordAuthenticationToken extends OAuth2ResourceOwnerBaseAuthenticationToken {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 构造OAuth2资源所有者密码认证令牌
     * @param authorizationGrantType 授权类型
     * @param clientPrincipal 客户端认证主体
     * @param scopes 权限范围集合
     * @param additionalParameters 附加参数映射
     */
    public OAuth2ResourceOwnerPasswordAuthenticationToken(AuthorizationGrantType authorizationGrantType,
                                                          Authentication clientPrincipal, Set<String> scopes, Map<String, Object> additionalParameters) {
        super(authorizationGrantType, clientPrincipal, scopes, additionalParameters);
    }

}
