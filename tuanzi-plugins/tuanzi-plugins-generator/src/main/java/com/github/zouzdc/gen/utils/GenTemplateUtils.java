package com.github.zouzdc.gen.utils;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.github.zouzdc.core.constant.CoreConstant;
import com.github.zouzdc.core.exception.TzException;
import com.github.zouzdc.core.utils.ResourceUtils;
import com.github.zouzdc.gen.domain.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * @description 代码生成器
 * @version 1.0.0
 * @date 2023/12/5 21:11
 * @author ZDC
 */
@Slf4j
public class GenTemplateUtils {

    public static void main(String[] args) {

        //寻找模版包信息,加载path.yml


        ClassPathResource classPathResource = new ClassPathResource("static/assets/test.txt");


            //configInfo = YamlUtil.load(resource.getInputStream(), TemplateConfigInfo.class);


        PathConfigInfo configInfo = new PathConfigInfo();




        Resource[] resources=null;
        try {
            resources = ResourceUtils.getResources("classpath*:/template/*/path.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(resources==null||resources.length==0){
            throw new TzException("没有检测到模版配置信息[path.json]");
        }
        for (Resource resource : resources) {
            if (!resource.exists()) {
                continue;
            }
            try {
                configInfo =JSONUtil.toBean(resource.getContentAsString(CoreConstant.UTF_8_CHARSET), PathConfigInfo.class);
            } catch (IOException e) {
                log.error("读取模版配置信息失败", e);
                continue;
            }
        }

        //检查模版信息
        checkTemplateInfo(configInfo);

        if(1==1)
            return;

        //数据库连接信息
        DbInfo dbInfo = new DbInfo(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/zdc?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8",
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

        //模版信息

        //转换


    }

    /**
     * 检查模版信息
     * @param configInfo
     */
    private static void checkTemplateInfo(PathConfigInfo configInfo) {
        if(configInfo==null || CollectionUtil.isEmpty(configInfo.getTemplates())){
            throw new TzException("模版信息不能为空");
        }
    }


    public  static void sqlInfo2JavaInfo( List<GenTable> list, PathConfigInfo configInfo) {
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