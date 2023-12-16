package com.github.zouzdc.gen.domain.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ZDC
 * @Description 模版信息
 * @Version 1.0.0
 * @Date 2023/12/5 21:23
 */
@Data
public class TemplateInfo implements Serializable {

    /**
     * 模版文件路径 例:mp/entity.vm ,mp/mapper.vm
     * 默认查找位置为src/main/resources/下, 模版文件放在一个专属的文件夹内
     */
    private String path;

    /**
     * 基础包路径
     */
    private String basePackage;
    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 相对包路径
     * 例如: pojo.entity , controller
     */
    private String relativePackage;

    /**
     * 文件名前缀
     */
    private String fileNamePrefix ="";

    /**
     * 文件名后缀
     */
    private String fileNameSuffix="";

    /**
     * 文件类型 不用添加.
     */
    private String fileType;

}