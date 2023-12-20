package com.github.zouzdc.gen.domain.pojo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * @author ZDC
 * @Description 模版配置信息
 * @Version 1.0.0
 * @Date 2023/12/5 21:28
 */
@Data
public class PathConfigInfo implements Serializable {

    private String name;
    private String version;
    private String description;

    /**
     * 数据库连接信息
     */
    private DbInfo dbInfo;

    /**
     * 模版信息列表
     */
    private List<TemplateInfo> templates = new ArrayList<>();

    /**
     * 全局变量, 可以在程序和模版中使用,会被局域变量替换
     */
    private GenVariable globalVariable = new GenVariable();


    /**
     * sql数据类型到Java数据类型映射表,覆盖默认配置
     */
    private List<TypeMappingInfo> typeMapping = new ArrayList<>();
    private List<TypeMappingInfo> defaultTypeMapping = new ArrayList<>();

    public PathConfigInfo() {
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
        defaultTypeMapping.add(new TypeMappingInfo("date", "LocalDate","java.time.LocalDate"));
        defaultTypeMapping.add(new TypeMappingInfo("datetime", "LocalDateTime","java.time.LocalDateTime"));
        defaultTypeMapping.add(new TypeMappingInfo("time", "LocalTime","java.time.LocalTime"));
        defaultTypeMapping.add(new TypeMappingInfo("timestamp", "Date","java.util.Date"));
        defaultTypeMapping.add(new TypeMappingInfo("year", "Year","java.time.Year"));

        defaultTypeMapping.add(new TypeMappingInfo(" ", "Integer"));
        defaultTypeMapping.add(new TypeMappingInfo("integer", "Integer"));
        defaultTypeMapping.add(new TypeMappingInfo("tinyint", "Integer"));
        defaultTypeMapping.add(new TypeMappingInfo("mediumint", "int"));
        defaultTypeMapping.add(new TypeMappingInfo("smallint", "short"));

        defaultTypeMapping.add(new TypeMappingInfo("bigint","Long"));

        defaultTypeMapping.add(new TypeMappingInfo("decimal", "BigDecimal","java.math.BigDecimal"));
        defaultTypeMapping.add(new TypeMappingInfo("numeric", "BigDecimal","java.math.BigDecimal"));
        defaultTypeMapping.add(new TypeMappingInfo("float", "float"));
        defaultTypeMapping.add(new TypeMappingInfo("real", "float"));
        defaultTypeMapping.add(new TypeMappingInfo("double", "double"));

        defaultTypeMapping.add(new TypeMappingInfo("char","String"));
        defaultTypeMapping.add(new TypeMappingInfo("varchar", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("text", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("json", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("longtext", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("mediumtext", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("tinytext", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("set", "String"));
        defaultTypeMapping.add(new TypeMappingInfo("enum", "String"));


        defaultTypeMapping.add(new TypeMappingInfo("bit", "boolean"));

        defaultTypeMapping.add(new TypeMappingInfo("binary", "byte[]"));
        defaultTypeMapping.add(new TypeMappingInfo("blob", "byte[]"));
        defaultTypeMapping.add(new TypeMappingInfo("longblob", "byte[]"));
        defaultTypeMapping.add(new TypeMappingInfo("mediumblob", "byte[]"));
        defaultTypeMapping.add(new TypeMappingInfo("tinyblob", "byte[]"));
        defaultTypeMapping.add(new TypeMappingInfo("varbinary", "byte[]"));

        defaultTypeMapping.add(new TypeMappingInfo("geometry", ""));
        defaultTypeMapping.add(new TypeMappingInfo("geometrycollection", ""));
        defaultTypeMapping.add(new TypeMappingInfo("linestring", ""));
        defaultTypeMapping.add(new TypeMappingInfo("multilinestring", ""));
        defaultTypeMapping.add(new TypeMappingInfo("multipoint", ""));
        defaultTypeMapping.add(new TypeMappingInfo("multipolygon", ""));
        defaultTypeMapping.add(new TypeMappingInfo("point", ""));
        defaultTypeMapping.add(new TypeMappingInfo("polygon", ""));


    }


    public Map<String, TypeMappingInfo> getTypeMapping2Map () {
        if(CollectionUtil.isNotEmpty(typeMapping)){
            this.defaultTypeMapping.addAll(typeMapping);
        }
       return this.defaultTypeMapping.stream().filter(x->StrUtil.isNotBlank(x.getSqlType())).collect(Collectors.toMap(TypeMappingInfo::getSqlType, Function.identity(),(x, y) -> y));
    }

    public Map<String, TypeMappingInfo> getTypeMapping2MapKeyLowerCase() {
        if(CollectionUtil.isNotEmpty(typeMapping)){
            this.defaultTypeMapping.addAll(typeMapping);
        }
        return this.typeMapping.stream().filter(x->StrUtil.isNotBlank(x.getSqlType())).collect(Collectors.toMap(x-> x.getSqlType().toLowerCase(),  Function.identity(),(x,y) -> y));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DbInfo getDbInfo() {
        return dbInfo;
    }

    public void setDbInfo(DbInfo dbInfo) {
        this.dbInfo = dbInfo;
    }

    public List<TemplateInfo> getTemplates() {
        return templates;
    }

    public void setTemplates(List<TemplateInfo> templates) {
        this.templates = templates;
    }

    public GenVariable getGlobalVariable() {
        return globalVariable;
    }

    public void setGlobalVariable(GenVariable globalVariable) {
        this.globalVariable = globalVariable;
    }

    public List<TypeMappingInfo> getTypeMapping() {
        return typeMapping;
    }

    public void setTypeMapping(List<TypeMappingInfo> typeMapping) {
        this.typeMapping = typeMapping;
    }

    public List<TypeMappingInfo> getDefaultTypeMapping() {
        return defaultTypeMapping;
    }

    public void setDefaultTypeMapping(List<TypeMappingInfo> defaultTypeMapping) {
        this.defaultTypeMapping = defaultTypeMapping;
    }
}