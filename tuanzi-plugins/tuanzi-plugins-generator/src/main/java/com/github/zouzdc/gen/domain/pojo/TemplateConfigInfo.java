package com.github.zouzdc.gen.domain.pojo;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author ZDC
 * @Description 模版配置信息
 * @Version 1.0.0
 * @Date 2023/12/5 21:28
 */
@Data
public class TemplateConfigInfo implements Serializable {


    /**
     * 模版信息列表
     */
    private List<TemplateInfo> templates = new ArrayList<>();

    /**
     * 全局变量, 可以在程序和模版中使用,会被局域变量替换
     */
    private GenVariable globalVariable = new GenVariable();


    /**
     * sql数据类型到Java数据类型映射表
     */
    private List<TypeMappingInfo> typeMapping = new ArrayList<>();

    public TemplateConfigInfo() {
        this.init ();
    }

    private void init () {


        //默认全局变量

        /**
         * 作者
         */
        globalVariable.put("author", "tuanzi");
        /**
         * 是否精准匹配类型映射
         * true: 精准匹配
         * false: 优先大小写,然后忽略大小写匹配
         */
        globalVariable.put("exactMatch", "false");




        //默认映射配置
        typeMapping.add(new TypeMappingInfo("date", "LocalDate","java.time.LocalDate"));
        typeMapping.add(new TypeMappingInfo("datetime", "LocalDateTime","java.time.LocalDateTime"));
        typeMapping.add(new TypeMappingInfo("time", "LocalTime","java.time.LocalTime"));
        typeMapping.add(new TypeMappingInfo("timestamp", "Date","java.util.Date"));
        typeMapping.add(new TypeMappingInfo("year", "Year","java.time.Year"));

        typeMapping.add(new TypeMappingInfo(" ", "Integer"));
        typeMapping.add(new TypeMappingInfo("integer", "Integer"));
        typeMapping.add(new TypeMappingInfo("tinyint", "Integer"));
        typeMapping.add(new TypeMappingInfo("mediumint", "int"));
        typeMapping.add(new TypeMappingInfo("smallint", "short"));

        typeMapping.add(new TypeMappingInfo("bigint","Long"));

        typeMapping.add(new TypeMappingInfo("decimal", "BigDecimal","java.math.BigDecimal"));
        typeMapping.add(new TypeMappingInfo("numeric", "BigDecimal","java.math.BigDecimal"));
        typeMapping.add(new TypeMappingInfo("float", "float"));
        typeMapping.add(new TypeMappingInfo("real", "float"));
        typeMapping.add(new TypeMappingInfo("double", "double"));

        typeMapping.add(new TypeMappingInfo("char","String"));
        typeMapping.add(new TypeMappingInfo("varchar", "String"));
        typeMapping.add(new TypeMappingInfo("text", "String"));
        typeMapping.add(new TypeMappingInfo("json", "String"));
        typeMapping.add(new TypeMappingInfo("longtext", "String"));
        typeMapping.add(new TypeMappingInfo("mediumtext", "String"));
        typeMapping.add(new TypeMappingInfo("tinytext", "String"));
        typeMapping.add(new TypeMappingInfo("set", "String"));
        typeMapping.add(new TypeMappingInfo("enum", "String"));


        typeMapping.add(new TypeMappingInfo("bit", "boolean"));

        typeMapping.add(new TypeMappingInfo("binary", "byte[]"));
        typeMapping.add(new TypeMappingInfo("blob", "byte[]"));
        typeMapping.add(new TypeMappingInfo("longblob", "byte[]"));
        typeMapping.add(new TypeMappingInfo("mediumblob", "byte[]"));
        typeMapping.add(new TypeMappingInfo("tinyblob", "byte[]"));
        typeMapping.add(new TypeMappingInfo("varbinary", "byte[]"));

        typeMapping.add(new TypeMappingInfo("geometry", ""));
        typeMapping.add(new TypeMappingInfo("geometrycollection", ""));
        typeMapping.add(new TypeMappingInfo("linestring", ""));
        typeMapping.add(new TypeMappingInfo("multilinestring", ""));
        typeMapping.add(new TypeMappingInfo("multipoint", ""));
        typeMapping.add(new TypeMappingInfo("multipolygon", ""));
        typeMapping.add(new TypeMappingInfo("point", ""));
        typeMapping.add(new TypeMappingInfo("polygon", ""));
    }


    public Map<String, TypeMappingInfo> getTypeMapping2Map () {
       return this.typeMapping.stream().filter(x->StrUtil.isNotBlank(x.getSqlType())).collect(Collectors.toMap(TypeMappingInfo::getSqlType, Function.identity(),(x, y) -> y));
    }

    public Map<String, TypeMappingInfo> getTypeMapping2MapKeyLowerCase() {
        return this.typeMapping.stream().filter(x->StrUtil.isNotBlank(x.getSqlType())).collect(Collectors.toMap(x-> x.getSqlType().toLowerCase(),  Function.identity(),(x,y) -> y));
    }
}