package com.github.zouzdc.config;

import com.github.zouzdc.pojo.R;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZDC
 * @Description 默认情况下，Spring Boot提供了一个 /error
 *              映射，以合理的方式处理所有错误，它被注册为servlet容器中的 “global” 错误页面。
 *              对于机器客户端，它产生一个JSON响应，包含错误的细节、HTTP状态和异常消息。
 *              此处参照org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
 * @Version 1.0.0
 * @Date 2023/11/13 0:28
 */
@RestController
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class MyErrorController implements ErrorController {




    @RequestMapping
    public R error(HttpServletRequest request) {
        Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = HttpStatus.resolve(code);
        HttpStatus httpStatus = (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
        return new R(500, httpStatus.toString(),"未知异常");
    }


}