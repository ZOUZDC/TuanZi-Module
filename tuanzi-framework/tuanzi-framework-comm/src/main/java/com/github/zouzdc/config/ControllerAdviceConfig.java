package com.github.zouzdc.config;

import com.github.zouzdc.exception.TzException;
import com.github.zouzdc.pojo.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ZDC
 * @Description //全局异常捕获
 * @Version 1.0.0
 * @Date 2023/11/2 23:29
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(HttpMessageConversionException.class)
    public R handleHttpMessageConversionException(HttpServletRequest request, Throwable ex) {
        return R.err("参数转换异常",ex);
    }
  @ExceptionHandler(TzException.class)
    public R handleTzException(HttpServletRequest request, Throwable ex) {
        return R.err(ex.getMessage(),ex);
    }


    /*放在最低下,兜底*/
    @ExceptionHandler(Exception.class)
    public R handleException(HttpServletRequest request, Throwable ex) {
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