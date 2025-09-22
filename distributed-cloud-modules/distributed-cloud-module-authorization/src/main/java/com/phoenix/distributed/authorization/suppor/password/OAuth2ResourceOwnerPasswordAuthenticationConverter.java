package com.phoenix.distributed.authorization.suppor.password;

import com.phoenix.distributed.authorization.suppor.base.OAuth2ResourceOwnerBaseAuthenticationConverter;
import com.phoenix.distributed.authorization.utils.OAuth2EndpointUtil;
import com.phoenix.distributed.common.constant.SecurityConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 * OAuth2 资源所有者密码认证转换器
 */
public class OAuth2ResourceOwnerPasswordAuthenticationConverter extends OAuth2ResourceOwnerBaseAuthenticationConverter<OAuth2ResourceOwnerPasswordAuthenticationToken> {

    /**
     * 支持密码模式
     * @param grantType 授权类型
     */
    @Override
    public boolean support(String grantType) {
        return SecurityConstants.PASSWORD.equals(grantType);
    }

    /**
     * 构建OAuth2资源所有者密码认证令牌
     * @param clientPrincipal 客户端主体认证信息
     * @param requestedScopes 请求的作用域集合
     * @param additionalParameters 附加参数映射
     * @return 构建完成的OAuth2资源所有者密码认证令牌
     */
    @Override
    public OAuth2ResourceOwnerPasswordAuthenticationToken buildToken(Authentication clientPrincipal, Set requestedScopes, Map additionalParameters) {
        return new OAuth2ResourceOwnerPasswordAuthenticationToken(new AuthorizationGrantType(SecurityConstants.PASSWORD), clientPrincipal,
                requestedScopes, additionalParameters);
    }

    /**
     * 校验扩展参数 密码模式密码必须不为空
     * @param request 参数列表
     */
    @Override
    public void checkParams(HttpServletRequest request) {
        MultiValueMap<String, String> parameters = OAuth2EndpointUtil.getParameters(request);
        // username (REQUIRED)
        String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
        if (!StringUtils.hasText(username) || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
            OAuth2EndpointUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.USERNAME,
                    OAuth2EndpointUtil.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }

        // password (REQUIRED)
        String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
        if (!StringUtils.hasText(password) || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
            OAuth2EndpointUtil.throwError(OAuth2ErrorCodes.INVALID_REQUEST, OAuth2ParameterNames.PASSWORD,
                    OAuth2EndpointUtil.ACCESS_TOKEN_REQUEST_ERROR_URI);
        }
    }

}
