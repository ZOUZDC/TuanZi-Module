package com.github.zouzdc.exception;

/**
 * @author ZDC
 * @version 1.0.0
 * @description 框架功能异常 TuanZi
 * @date 2023/11/2 23:31
 */
public class TzException extends RuntimeException {
    public TzException() {
    }

    public TzException(String message) {
        super(message);
    }

    public TzException(String message, Throwable cause) {
        super(message, cause);
    }

    public TzException(Throwable cause) {
        super(cause);
    }

    public TzException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}