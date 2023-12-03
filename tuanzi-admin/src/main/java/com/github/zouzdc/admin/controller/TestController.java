package com.github.zouzdc.admin.controller;

import com.github.zouzdc.framework.base.BaseQueryVo;
import com.github.zouzdc.core.exception.TzException;
import com.github.zouzdc.core.pojo.R;
import com.github.zouzdc.admin.pojo.TestXx;
import org.springframework.web.bind.annotation.*;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/11/2 23:44
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/")
    public R ok() {
        return R.ok();
    }

    @GetMapping("/err")
    public R err() {
        if (1 / 0 == 1)
            throw new TzException("错误");
        return R.ok();
    }

    @PostMapping("/test")
    public R test(@RequestBody BaseQueryVo vo) {
        return R.ok(vo);
    }

    @PostMapping("/testBody")
    public R testBody(@RequestBody TestXx vo) {
        return R.ok(vo);
    }

    @PostMapping("/testPath")
    public R testPath(TestXx vo) {
        System.out.println(vo.toString());
        return R.ok(vo);
    }

}