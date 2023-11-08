package com.github.zouzdc.controller;

import com.github.zouzdc.exception.TzException;
import com.github.zouzdc.pojo.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/11/2 23:44
 */
@RestController
public class TestController {
    @RequestMapping("/")
    public R ok() {
        return R.ok();
    }

    @RequestMapping("/err")
    public R err() {
        if (1 / 0 == 1)
            throw new TzException("错误");
        return R.ok();
    }
}