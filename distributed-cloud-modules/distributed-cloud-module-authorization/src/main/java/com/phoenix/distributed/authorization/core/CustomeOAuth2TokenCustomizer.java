package com.phoenix.distributed.authorization.core;

import com.phoenix.distributed.authorization.service.PigUser;
import com.phoenix.distributed.common.constant.SecurityConstants;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenClaimsSet;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * OAuth2 Token 自定义增强实现类
 */
public class CustomeOAuth2TokenCustomizer implements OAuth2TokenCustomizer<OAuth2TokenClaimsContext> {

    /**
     * 自定义OAuth 2.0 Token属性
     * @param context 包含OAuth 2.0 Token属性的上下文
     */
    @Override
    public void customize(OAuth2TokenClaimsContext context) {
        OAuth2TokenClaimsSet.Builder claims = context.getClaims();
        claims.claim(SecurityConstants.DETAILS_LICENSE, SecurityConstants.PROJECT_LICENSE);
        String clientId = context.getAuthorizationGrant().getName();
        claims.claim(SecurityConstants.CLIENT_ID, clientId);
        // 客户端模式不返回具体用户信息
        if (SecurityConstants.CLIENT_CREDENTIALS.equals(context.getAuthorizationGrantType().getValue())) {
            return;
        }

        PigUser pigUser = (PigUser) context.getPrincipal().getPrincipal();
        claims.claim(SecurityConstants.DETAILS_USER, pigUser);
        claims.claim(SecurityConstants.DETAILS_USER_ID, pigUser.getId());
        claims.claim(SecurityConstants.USERNAME, pigUser.getUsername());
    }

}
