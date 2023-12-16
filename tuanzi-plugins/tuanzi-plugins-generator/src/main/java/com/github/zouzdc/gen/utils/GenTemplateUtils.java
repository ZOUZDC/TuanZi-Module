package com.github.zouzdc.gen.utils;


import cn.hutool.core.collection.CollectionUtil;
import com.github.zouzdc.core.exception.TzException;
import com.github.zouzdc.gen.domain.pojo.*;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Map;

/**
 * @description 代码生成器
 * @version 1.0.0
 * @date 2023/12/5 21:11
 * @author ZDC
 */
public class GenTemplateUtils {

    public static void main1(String[] args) {

        //寻找模版包信息,加载path.yml


        ClassPathResource classPathResource = new ClassPathResource("static/assets/test.txt");


            //configInfo = YamlUtil.load(resource.getInputStream(), TemplateConfigInfo.class);


        TemplateConfigInfo configInfo = new TemplateConfigInfo();

        //检查模版信息
        checkTemplateInfo(configInfo);

        if(1==1)
            return;

        //数据库连接信息
        DbInfo dbInfo = new DbInfo(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/ry-vue?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8",
                "root",
                "123123",
                "ry-vue"
        );

        //
        List<GenTable> list = GenDbUtil.getTableInfos(dbInfo,"sys_dept");



        //sql到实体类型转换信息
        sqlInfo2JavaInfo(list,configInfo);



        //全局定义参量
        Map<String, String> globalVariable = configInfo.getGlobalVariable();

        //sql到实体类型转换信息

        //模版信息

        //转换


    }

    /**
     * 检查模版信息
     * @param configInfo
     */
    private static void checkTemplateInfo(TemplateConfigInfo configInfo) {
        List<TemplateInfo> templates = configInfo.getTemplates();
        if(CollectionUtil.isEmpty(templates)){
            throw new TzException("模版信息不能为空");
        }



    }


    public  static void sqlInfo2JavaInfo( List<GenTable> list, TemplateConfigInfo configInfo) {
        if(CollectionUtil.isEmpty(list)){
            return;
        }

        GenVariable globalVariable = configInfo.getGlobalVariable();

        Map<String, TypeMappingInfo> typeMap = configInfo.getTypeMapping2Map();
        Map<String, TypeMappingInfo> typeMapLc=null;

        //加载小写映射关系
        if (!"true".equalsIgnoreCase(globalVariable.getExactMatch())) {
            typeMapLc = configInfo.getTypeMapping2MapKeyLowerCase();
        }


        for (GenTable genTable : list) {

            //表名信息
            genTable.setClassNameLc(GenUtil.column2Property(genTable.getTableName()));
            genTable.setClassNameUc(GenUtil.toUpFirst(genTable.getClassNameLc()));

            //字段信息
            for (GenTableFiled filed : genTable.getFiledInfos()) {
                filed.setFiledName(GenUtil.column2Property(filed.getColumnName()));
                tableFiledMapping(filed,typeMap,typeMapLc);
                filed.setColumnComment(filed.getColumnComment().toLowerCase());
            }
        }

    }

    public static void tableFiledMapping(GenTableFiled filed,  Map<String, TypeMappingInfo> typeMap , Map<String, TypeMappingInfo> typeMapLc){
        if (typeMap!=null && typeMap.containsKey(filed.getColumnType())) {
            filed.setFiledType(typeMap.get(filed.getColumnType()).getJavaType());
            filed.setImportInfo(typeMap.get(filed.getColumnType()).getImportInfo());
            return;
        }
        String lowerCase = filed.getColumnType().toLowerCase();
        if (typeMapLc!=null && typeMapLc.containsKey(lowerCase)) {
            filed.setFiledType(typeMapLc.get(lowerCase).getJavaType());
            filed.setImportInfo(typeMapLc.get(lowerCase).getImportInfo());
            return;
        }
        filed.setFiledType("Unknown");
    }


}