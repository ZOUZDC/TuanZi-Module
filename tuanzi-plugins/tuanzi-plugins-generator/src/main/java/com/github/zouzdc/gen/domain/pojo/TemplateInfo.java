package com.github.zouzdc.gen.domain.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZDC
 * @Version 1.0.0
 * @Date 2023/12/5 21:23
 * @Description 模版信息
 *
 *   字段解释:
 *   此文件的资源路径为: src\main\java\com\github\zouzdc\gen\domain\pojo\TemplateInfo.java
 *   包路径 com.github.zouzdc.gen.domain.pojo.TemplateInfo
 *
 *   基础包路径: com.github.zouzdc
 *   模块名: gen
 *   相对包路径: domain.pojo
 *   生成的文件名前缀: 此处无
 *   生成的文件名后缀: 此处无
 *   文件类型: .java
 *   前置目录: src.main.java ,
 *   强制文件名: 无
 *
 */
@Data
public class TemplateInfo implements Serializable {

    /**
     * 模版文件路径 例:mp/entity.vm ,mp/mapper.vm
     * 默认查找位置为src/main/resources/template/ *\/下, 模版文件放在一个专属的文件夹内
     */
    private String path;

    /**
     * 基础包路径,参与转换目录,会被全局变量替换
     */
    private String basePackage;
    /**
     * 模块名,参与转换目录,会被全局变量替换
     */
    private String moduleName;

    /**
     * 相对包路径,参与转换目录
     * 例如: pojo.entity , controller
     */
    private String relativePackage;

    /**
     * 生成的文件名前缀
     */
    private String fileNamePrefix ="";

    /**
     * 生成的文件名后缀
     */
    private String fileNameSuffix="";

    /**
     * 文件类型 需带点, 如:.java
     */
    private String fileType="";

    /**
     * 前置目录 ,支持特殊变量[zdc],则以文件名替换目录层级 ,如 vue\[zdc] ,最终生成目录为 src\(fileNamePrefix+tableName+fileNameSuffix)
     */
    private String prePath = "";
    /**
     * 文件名,如果存在值,则以此值作为文件名
     * 支持特殊变量[tz],文件名中[tz]将被替换为文件名,如: [tz]-aou,则最终文件名为: (fileNamePrefix+tableName+fileNameSuffix)-aou
     * 如无特殊变量值 ,则 fileNamePrefix,fileNameSuffix规则将不再生效
     */
    private String forceName="";




}