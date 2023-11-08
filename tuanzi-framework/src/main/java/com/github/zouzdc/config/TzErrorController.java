package com.github.zouzdc.config;

import com.github.zouzdc.pojo.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZDC
 * @Description Error页面处理, ControllerAdvice无法捕获的异常. 参照BasicErrorController
 * @Link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController
 * @Version 1.0.0
 * @Date 2023/11/3 0:29
 */

@RestController
@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class TzErrorController implements ErrorController {


    @RequestMapping
    public R error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        return R.err(status.toString());
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }


}