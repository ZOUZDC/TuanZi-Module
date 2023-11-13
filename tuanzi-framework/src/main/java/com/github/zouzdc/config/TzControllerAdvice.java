package com.github.zouzdc.config;

import com.github.zouzdc.exception.TzException;
import com.github.zouzdc.pojo.R;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author ZDC
 * @Description //全局异常捕获
 * @Version 1.0.0
 * @Date 2023/11/2 23:29
 */
@Slf4j
@RestControllerAdvice("com.github.zouzdc")
public class TzControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TzException.class)
    public R handelTzException(HttpServletRequest request, Throwable ex) {
        return R.err(ex);
    }


    /*放在最低下,兜底*/
    @ExceptionHandler(Exception.class)
    public R handelException(HttpServletRequest request, Throwable ex) {
        return R.err(ex);
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