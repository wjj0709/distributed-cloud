package com.phoenix.distributed.authorization.filter;

import com.phoenix.distributed.authorization.properties.AuthSecurityConfigProperties;
import com.phoenix.distributed.common.exception.ValidateCodeException;
import com.phoenix.distributed.common.utils.RedisUtil;
import com.phoenix.distributed.common.utils.StringUtil;
import com.phoenix.distributed.common.utils.WebUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 验证码过滤器：用于处理登录请求中的验证码校验
 */
@Component
@RequiredArgsConstructor
public class ValidateCodeFilter extends OncePerRequestFilter {
    /**
     * 默认登录URL
     */
    private static final String OAUTH_TOKEN_URL = "/oauth2/token";

    /**
     * grant_type
     */
    private static final String REFRESH_TOKEN = "refresh_token";

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";

    /**
     * password 模式
     */
    String PASSWORD = "password";

    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 授权码
     */
    String AUTHORIZATION_CODE = "authorization_code";

    private final AuthSecurityConfigProperties authSecurityConfigProperties;

    /**
     * 过滤器内部处理逻辑，用于验证码校验
     * @param request HTTP请求
     * @param response HTTP响应
     * @param filterChain 过滤器链
     * @throws ServletException Servlet异常
     * @throws IOException IO异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestUrl = request.getServletPath();

        // 不是登录URL 请求直接跳过
        if (!OAUTH_TOKEN_URL.equals(requestUrl)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果登录URL 但是刷新token的请求，直接向下执行
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (StringUtil.equals(REFRESH_TOKEN, grantType)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 如果是密码模式 && 客户端不需要校验验证码
        boolean isIgnoreClient = authSecurityConfigProperties.getIgnoreClients().contains(WebUtil.getClientId());
        if (StringUtil.equalsAnyIgnoreCase(grantType, PASSWORD, CLIENT_CREDENTIALS, AUTHORIZATION_CODE) && isIgnoreClient) {
            filterChain.doFilter(request, response);
            return;
        }

        // 校验验证码 1. 客户端开启验证码 2. 短信模式
        try {
            checkCode();
            filterChain.doFilter(request, response);
        }
        catch (ValidateCodeException validateCodeException) {
            throw new OAuth2AuthenticationException(validateCodeException.getMessage());
        }
    }

    /**
     * 校验验证码
     */
    private void checkCode() throws ValidateCodeException {
        Optional<HttpServletRequest> request = WebUtil.getRequest();
        String code = request.get().getParameter("code");

        if (StringUtil.isBlank(code)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        String randomStr = request.get().getParameter("randomStr");

        // https://gitee.com/log4j/pig/issues/IWA0D
        String mobile = request.get().getParameter("mobile");
        if (StringUtil.isNotBlank(mobile)) {
            randomStr = mobile;
        }

        String key = DEFAULT_CODE_KEY + randomStr;
        if (!RedisUtil.hasKey(key)) {
            throw new ValidateCodeException("验证码不合法");
        }

        String saveCode = RedisUtil.get(key);

        if (StringUtil.isBlank(saveCode)) {
            RedisUtil.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }

        if (!StringUtil.equals(saveCode, code)) {
            RedisUtil.delete(key);
            throw new ValidateCodeException("验证码不合法");
        }
    }
}
