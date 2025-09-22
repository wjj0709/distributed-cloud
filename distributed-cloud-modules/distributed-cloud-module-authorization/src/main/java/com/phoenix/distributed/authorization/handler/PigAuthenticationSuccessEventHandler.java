package com.phoenix.distributed.authorization.handler;

import com.alibaba.nacos.common.utils.MapUtil;
import com.phoenix.distributed.authorization.convert.CustomOAuth2AccessTokenResponseHttpMessageConverter;
import com.phoenix.distributed.authorization.service.PigUser;
import com.phoenix.distributed.common.holder.SpringContextHolder;
import com.phoenix.distributed.common.utils.StringUtil;
import com.phoenix.distributed.entity.SysLog;
import com.phoenix.distributed.framework.logger.event.SysLogEvent;
import com.phoenix.distributed.framework.logger.utils.SysLogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 处理认证成功事件的处理器
 */
@Slf4j
public class PigAuthenticationSuccessEventHandler implements AuthenticationSuccessHandler {
    /**
     * 用户信息
     */
    String DETAILS_USER = "user_info";
    /**
     * 请求开始时间
     */
    String REQUEST_START_TIME = "REQUEST-START-TIME";

    private final HttpMessageConverter<OAuth2AccessTokenResponse> accessTokenHttpResponseConverter = new CustomOAuth2AccessTokenResponseHttpMessageConverter();

    /**
     * 用户认证成功时调用
     * @param request 触发认证成功的请求
     * @param response 响应对象
     * @param authentication 认证过程中创建的认证对象
     */
    @SneakyThrows
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;
        Map<String, Object> map = accessTokenAuthentication.getAdditionalParameters();
        if (MapUtil.isNotEmpty(map)) {
            // 发送异步日志事件
            PigUser userInfo = (PigUser) map.get(DETAILS_USER);
            log.info("用户：{} 登录成功", userInfo.getName());
            SecurityContextHolder.getContext().setAuthentication(accessTokenAuthentication);
            SysLog logVo = SysLogUtil.getSysLog();
            logVo.setTitle("登录成功");
            String startTimeStr = request.getHeader(REQUEST_START_TIME);
            if (StringUtil.isNotBlank(startTimeStr)) {
                Long startTime = Long.parseLong(startTimeStr);
                Long endTime = System.currentTimeMillis();
                logVo.setTime(endTime - startTime);
            }
            logVo.setCreateBy(userInfo.getName());
            SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        }

        // 输出token
        sendAccessTokenResponse(request, response, authentication);
    }

    /**
     * 发送访问令牌响应
     * @param request HTTP请求
     * @param response HTTP响应
     * @param authentication 认证信息
     * @throws IOException 写入响应时可能抛出IO异常
     */
    private void sendAccessTokenResponse(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        OAuth2AccessTokenAuthenticationToken accessTokenAuthentication = (OAuth2AccessTokenAuthenticationToken) authentication;

        OAuth2AccessToken accessToken = accessTokenAuthentication.getAccessToken();
        OAuth2RefreshToken refreshToken = accessTokenAuthentication.getRefreshToken();
        Map<String, Object> additionalParameters = accessTokenAuthentication.getAdditionalParameters();

        OAuth2AccessTokenResponse.Builder builder = OAuth2AccessTokenResponse.withToken(accessToken.getTokenValue())
                .tokenType(accessToken.getTokenType())
                .scopes(accessToken.getScopes());
        if (accessToken.getIssuedAt() != null && accessToken.getExpiresAt() != null) {
            builder.expiresIn(ChronoUnit.SECONDS.between(accessToken.getIssuedAt(), accessToken.getExpiresAt()));
        }
        if (refreshToken != null) {
            builder.refreshToken(refreshToken.getTokenValue());
        }
        if (!CollectionUtils.isEmpty(additionalParameters)) {
            builder.additionalParameters(additionalParameters);
        }
        OAuth2AccessTokenResponse accessTokenResponse = builder.build();
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);

        // 无状态 注意删除 context 上下文的信息
        SecurityContextHolder.clearContext();

        this.accessTokenHttpResponseConverter.write(accessTokenResponse, null, httpResponse);
    }

}