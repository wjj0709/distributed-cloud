package com.phoenix.distributed.authorization.handler;

import com.phoenix.distributed.common.holder.SpringContextHolder;
import com.phoenix.distributed.common.lang.R;
import com.phoenix.distributed.common.utils.StringUtil;
import com.phoenix.distributed.entity.SysLog;
import com.phoenix.distributed.framework.logger.enums.LogTypeEnum;
import com.phoenix.distributed.framework.logger.event.SysLogEvent;
import com.phoenix.distributed.framework.logger.utils.SysLogUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 认证失败处理器：处理用户认证失败事件并记录日志
 */
@Slf4j
public class PigAuthenticationFailureEventHandler implements AuthenticationFailureHandler {
    /**
     * 请求开始时间
     */
    String REQUEST_START_TIME = "REQUEST-START-TIME";

    private final MappingJackson2HttpMessageConverter errorHttpResponseConverter = new MappingJackson2HttpMessageConverter();

    /**
     * 当认证失败时调用
     * @param request 认证请求
     * @param response 认证响应
     * @param exception 认证失败的异常
     */
    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String username = request.getParameter(OAuth2ParameterNames.USERNAME);

        log.info("用户：{} 登录失败，异常：{}", username, exception.getLocalizedMessage());
        SysLog logVo = SysLogUtil.getSysLog();
        logVo.setTitle("登录失败");
        logVo.setLogType(LogTypeEnum.ERROR.getType());
        logVo.setException(exception.getLocalizedMessage());
        // 发送异步日志事件
        String startTimeStr = request.getHeader(REQUEST_START_TIME);
        if (StringUtil.isNotBlank(startTimeStr)) {
            Long startTime = Long.parseLong(startTimeStr);
            Long endTime = System.currentTimeMillis();
            logVo.setTime(endTime - startTime);
        }
        logVo.setCreateBy(username);
        SpringContextHolder.publishEvent(new SysLogEvent(logVo));
        // 写出错误信息
        sendErrorResponse(request, response, exception);
    }

    /**
     * 发送错误响应
     * @param request HTTP请求
     * @param response HTTP响应
     * @param exception 认证异常
     * @throws IOException 写入响应时发生IO异常
     */
    private void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        httpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);
        String errorMessage;

        if (exception instanceof OAuth2AuthenticationException) {
            OAuth2AuthenticationException authorizationException = (OAuth2AuthenticationException) exception;
            errorMessage = StringUtil.isBlank(authorizationException.getError().getDescription())
                    ? authorizationException.getError().getErrorCode()
                    : authorizationException.getError().getDescription();
        }
        else {
            errorMessage = exception.getLocalizedMessage();
        }

        this.errorHttpResponseConverter.write(R.failure(errorMessage), MediaType.APPLICATION_JSON, httpResponse);
    }

}
