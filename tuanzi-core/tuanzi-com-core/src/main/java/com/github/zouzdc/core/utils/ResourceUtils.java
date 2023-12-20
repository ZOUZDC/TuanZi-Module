package com.github.zouzdc.core.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * 获取资源文件
 * classpath:/excel/导出模板.xlsx
 * classpath*:/excel/导入模板.xlsx"
 * classpath*:/excel/*
 *
 * @author ZDC
 * @date 2023/3/3 19:53
 */
public class ResourceUtils {

    public static Resource getResource(String path) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResource(path);
    }

    public static Resource[] getResources(String path) throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        return resolver.getResources(path);
    }

}