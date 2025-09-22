package com.phoenix.distributed.framework.logger.event;

import com.phoenix.distributed.entity.SysLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 * 系统日志事件源类，继承自SysLog
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysLogEventSource extends SysLog {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 参数重写成object
     */
    private Object body;

}
