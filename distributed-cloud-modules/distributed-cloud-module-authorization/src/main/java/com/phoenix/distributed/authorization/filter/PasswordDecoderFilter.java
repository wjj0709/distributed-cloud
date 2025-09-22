package com.phoenix.distributed.authorization.filter;

import com.phoenix.distributed.authorization.properties.AuthSecurityConfigProperties;
import com.phoenix.distributed.common.servlet.RepeatBodyRequestWrapper;
import com.phoenix.distributed.common.utils.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 密码解密过滤器：用于处理登录请求中的密码解密
 */
@Component
@RequiredArgsConstructor
public class PasswordDecoderFilter extends OncePerRequestFilter {
    /**
     * 默认登录URL
     */
    private static final String OAUTH_TOKEN_URL = "/oauth2/token";
    private final AuthSecurityConfigProperties authSecurityConfigProperties;

    private static final String PASSWORD = "password";

    private static final String KEY_ALGORITHM = "AES";

    static {
        // 关闭hutool 强制关闭Bouncy Castle库的依赖
        // SecureUtil.disableBouncyCastle();
    }

    /**
     * 过滤器内部处理逻辑，用于处理登录请求中的密码解密
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param chain 过滤器链
     * @throws ServletException 如果发生servlet相关异常
     * @throws IOException 如果发生I/O异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 不是登录请求，直接向下执行
        if (!StringUtil.containsAnyIgnoreCase(request.getRequestURI(), OAUTH_TOKEN_URL)) {
            chain.doFilter(request, response);
            return;
        }

        // 将请求流转换为可多次读取的请求流
        RepeatBodyRequestWrapper requestWrapper = new RepeatBodyRequestWrapper(request);
        Map<String, String[]> parameterMap = requestWrapper.getParameterMap();

        // 构建前端对应解密AES 因子
        // AES aes = new AES(Mode.CFB, Padding.NoPadding, new SecretKeySpec(authSecurityConfigProperties.getEncodeKey().getBytes(), KEY_ALGORITHM), new IvParameterSpec(authSecurityConfigProperties.getEncodeKey().getBytes()));
        //
        // parameterMap.forEach((k, v) -> {
        //     String[] values = parameterMap.get(k);
        //     if (!PASSWORD.equals(k) || ArrayUtil.isEmpty(values)) {
        //         return;
        //     }
        //
        //     // 解密密码
        //     String decryptPassword = aes.decryptStr(values[0]);
        //     parameterMap.put(k, new String[] { decryptPassword });
        // });
        chain.doFilter(requestWrapper, response);
    }

}
