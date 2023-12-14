package com.github.zouzdc.gen.utils;

import com.github.zouzdc.gen.domain.pojo.DbInfo;
import com.github.zouzdc.gen.domain.pojo.GenTable;
import com.github.zouzdc.gen.domain.pojo.GenTableFiled;

import java.sql.*;
import java.util.List;
import java.util.Objects;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/12/6 23:57
 */
public class GenUtil {




    /**
     * 下划线转驼峰 首字母小写
     *
     * @param fieldName
     * @return
     */
    public static String column2Property(String fieldName) {
        StringBuffer result = new StringBuffer();

        fieldName = fieldName.toLowerCase();
        String[] fields = fieldName.split("_");
        for (int i = 0; i < fields.length; i++) {
            String field = fields[i];
            if (i == 0) {
                result.append(field);
            } else {
                result.append(toUpFirst(field));
            }
        }
        return result.toString();
    }

    /**
     * 首字母大写
     *
     * @param className
     * @return
     */
    public static String toUpFirst(String className) {
        if(className==""){
            return "";
        }
        char[] cs = className.toCharArray();
        if(cs[0]>96 && cs[0]<123){
            cs[0] -= 32;
            className = String.valueOf(cs);
        }
        return className;
    }

    /**
     * 首字母小写
     *
     * @param className
     * @return
     */
    public static String toLowFirst(String className) {
        if(className==""){
            return "";
        }
        char[] cs = className.toCharArray();
        if(cs[0]>64 && cs[0]<91){
            cs[0] += 32;
            className = String.valueOf(cs);
        }
        return className;
    }

}