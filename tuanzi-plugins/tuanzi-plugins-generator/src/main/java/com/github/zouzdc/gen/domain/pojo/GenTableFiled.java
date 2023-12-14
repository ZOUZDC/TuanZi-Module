package com.github.zouzdc.gen.domain.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/12/5 23:38
 */
@Data
@NoArgsConstructor
public class GenTableFiled implements Serializable {

    /**
     *  字段名
     */
    private String columnName;

    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 是否主键
     */
    private boolean primaryKey;
    /**
     * 是否为空
     */
    private boolean columnNotNull;
    /**
     * 字段注释
     */
    private String columnComment;

    /**
     * Java属性名
     */
    private String filedName;
    /**
     * Java属性类型
     */
    private String filedType;

    /**
     * 导入信息
     */
    private String importInfo;

    public GenTableFiled(String columnName, String columnType, boolean primaryKey, boolean columnNotNull, String columnComment) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.primaryKey = primaryKey;
        this.columnNotNull = columnNotNull;
        this.columnComment = columnComment;
    }
}