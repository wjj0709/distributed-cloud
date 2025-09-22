package com.phoenix.distributed.framework.logger.event;

import com.phoenix.distributed.entity.SysLog;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
public class SysLogEvent extends ApplicationEvent {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 构造方法，根据源SysLog对象创建SysLogEvent
     * @param source 源SysLog对象
     */
    public SysLogEvent(SysLog source) {
        super(source);
    }

}
