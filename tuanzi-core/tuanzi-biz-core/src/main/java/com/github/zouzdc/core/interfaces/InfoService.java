package com.github.zouzdc.core.interfaces;

import cn.hutool.core.lang.Pair;

import java.util.List;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/12/4 22:47
 */
public interface InfoService {
     default List<Pair<String,String>> getRunnerInfo(){
        return List.of();
    }
}