package com.github.zouzdc.framework.config;

import com.github.zouzdc.core.exception.TzException;
import com.github.zouzdc.core.pojo.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description 全局异常捕获
 * @version 1.0.0
 * @date 2023/12/2 22:26
 * @author ZDC
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(HttpMessageConversionException.class)
    public R handleHttpMessageConversionException(HttpServletRequest request, Throwable ex) {
        log.warn("参数转换异常",ex);
        return R.err("参数转换异常",ex);
    }
  @ExceptionHandler(TzException.class)
    public R handleTzException(HttpServletRequest request, Throwable ex) {
        return R.err(ex.getMessage(),ex);
    }


    /*放在最低下,兜底*/
    @ExceptionHandler(Exception.class)
    public R handleException(HttpServletRequest request, Throwable ex) {
        log.warn("系统异常{}",ex);
        return R.err(null,ex);
    }

/*

    @ResponseBody
    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new MyErrorBody(status.value(), ex.getMessage()), status);
    }
*/




}