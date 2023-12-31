package com.github.zouzdc.gen.domain.pojo;

import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author ZDC
 * @Description 模版配置信息
 * @Version 1.0.0
 * @Date 2023/12/5 21:28
 */
@Data
public class GenVariable extends HashMap<String, String> {

    /**
     *     作者
     */
    public static final String author="author";

    /**
     * 精确匹配
     * TRUE: 精确匹配
     * FALSE: 小写匹配
     */
    public static final String exactMatch="exactMatch";


    public GenVariable() {
        super();

        put(author,"");
        put(exactMatch,"");



    }


}