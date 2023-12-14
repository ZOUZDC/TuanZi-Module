package com.github.zouzdc.gen.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZDC
 * @Description sql数据类型到Java数据类型映射信息
 * @Version 1.0.0
 * @Date 2023/12/5 22:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeMappingInfo implements Serializable {

    /**
     * sql数据类型 一定是小写
     */
    private String sqlType;

    /**
     * java数据类型
     */
    private String javaType;

    /**
     * 导入信息
     */
    private String importInfo;

    public TypeMappingInfo(String sqlType, String javaType) {
        this.sqlType = sqlType;
        this.javaType = javaType;
    }

}