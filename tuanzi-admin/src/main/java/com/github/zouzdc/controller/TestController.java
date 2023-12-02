package com.github.zouzdc.controller;

import com.github.zouzdc.base.BaseEntity;
import com.github.zouzdc.base.BaseQueryVo;
import com.github.zouzdc.exception.TzException;
import com.github.zouzdc.pojo.R;
import com.github.zouzdc.pojo.TestXx;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/test")
    public R test(@RequestBody BaseQueryVo vo) {
        return R.ok(vo);
    }

    @PostMapping("/test2")
    public R test2(@RequestBody TestXx vo) {
        return R.ok(vo);
    }

    @PostMapping("/test3")
    public R test3(TestXx vo) {
        return R.ok(vo);
    }

}