package com.github.zouzdc.gen.domain.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZDC
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/12/5 23:38
 */
@Data
public class GenTable implements Serializable {

    /**
     * 表名称
     */
    private String tableName;
    /**
     * Java类名(首字母大写) ClassName
     */
    private String classNameUc;
    /**
     * Java类名(首字母小写) className
     */
    private String classNameLc;
    /**
     * 字段信息
     */
    private List<GenTableFiled> filedInfos =new ArrayList<>();


}