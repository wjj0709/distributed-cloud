package com.phoenix.distributed.framework.logger.utils;

import com.phoenix.distributed.common.holder.SpringContextHolder;
import com.phoenix.distributed.common.utils.ArrayUtil;
import com.phoenix.distributed.common.utils.MapUtil;
import com.phoenix.distributed.common.utils.SpringUtil;
import com.phoenix.distributed.framework.logger.enums.LogTypeEnum;
import com.phoenix.distributed.framework.logger.event.SysLogEventSource;
import com.phoenix.distributed.framework.logger.properties.PigLogProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 系统日志工具类
 */
public class SysLogUtil {
    /**
     * 获取系统日志事件源
     * @return 系统日志事件源对象
     */
    public static SysLogEventSource getSysLog() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        SysLogEventSource sysLog = new SysLogEventSource();
        sysLog.setLogType(LogTypeEnum.NORMAL.getType());
        sysLog.setRequestUri(request.getRequestURI());
        sysLog.setMethod(request.getMethod());
        // sysLog.setRemoteAddr(JakartaServletUtil.getClientIP(request));
        sysLog.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        sysLog.setCreateBy("");
        sysLog.setServiceId(SpringUtil.getProperty("spring.application.name"));

        // get 参数脱敏
        PigLogProperties logProperties = SpringContextHolder.getBean(PigLogProperties.class);
        Map<String, String[]> paramsMap = MapUtil.removeAny(new HashMap<>(request.getParameterMap()), ArrayUtil.toArray(logProperties.getExcludeFields(), String.class));
        sysLog.setParams(paramsMap.toString());
        return sysLog;
    }

    /**
     * 获取spel 定义的参数值
     * @param context 参数容器
     * @param key key
     * @param clazz 需要返回的类型
     * @param <T> 返回泛型
     * @return 参数值
     */
    public <T> T getValue(EvaluationContext context, String key, Class<T> clazz) {
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(key);
        return expression.getValue(context, clazz);
    }

    /**
     * 获取参数容器
     * @param arguments 方法的参数列表
     * @param signatureMethod 被执行的方法体
     * @return 装载参数的容器
     */
    public EvaluationContext getContext(Object[] arguments, Method signatureMethod) {
        String[] parameterNames = new StandardReflectionParameterNameDiscoverer().getParameterNames(signatureMethod);
        EvaluationContext context = new StandardEvaluationContext();
        if (parameterNames == null) {
            return context;
        }
        for (int i = 0; i < arguments.length; i++) {
            context.setVariable(parameterNames[i], arguments[i]);
        }
        return context;
    }
}
