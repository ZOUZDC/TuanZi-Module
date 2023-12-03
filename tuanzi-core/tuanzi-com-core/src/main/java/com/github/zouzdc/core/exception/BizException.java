package com.github.zouzdc.core.exception;

/**
 * @author ZDC
 * @version 1.0.0
 * @description 业务异常
 * @date 2023/11/2 23:31
 */
public class BizException extends RuntimeException {
    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}