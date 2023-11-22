package com.github.zouzdc.base;

import com.github.zouzdc.pojo.R;
import org.springframework.web.bind.annotation.GetMapping;


public class BaseController<E,V,S extends BaseService<E,V>>{


    @GetMapping("/page")
    public R page(){
        return R.ok();
    }



}