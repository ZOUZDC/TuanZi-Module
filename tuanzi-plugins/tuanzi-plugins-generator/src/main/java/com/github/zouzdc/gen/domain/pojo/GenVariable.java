package com.github.zouzdc.gen.domain.pojo;

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
    private String author;
    /**
     * 精确匹配
     * true: 精确匹配
     * false: 小写匹配
     */
    private String exactMatch;

}