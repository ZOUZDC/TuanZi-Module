package com.github.zouzdc.gen.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.github.zouzdc.core.exception.TzException;
import com.github.zouzdc.gen.domain.pojo.DbInfo;
import com.github.zouzdc.gen.domain.pojo.GenTable;
import com.github.zouzdc.gen.domain.pojo.GenTableFiled;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据库工具类
 */
@Slf4j
@NoArgsConstructor
public class GenDbUtil {


    public GenDbUtil(DbInfo dbInfo) {
        this.dbInfo = dbInfo;
        this.init();
    }

    /**
     * 数据库连接信息
     */
    private DbInfo dbInfo;
    /**
     * 数据库连接
     */
    private Connection conn;

    /**
     * 查询单个表的字段信息 (MySQL)
     */
    private static  String SINGLE_TABLE_SQL= """
                    SELECT  COLUMN_NAME, DATA_TYPE, COLUMN_KEY  , IS_NULLABLE , COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS
                    where table_schema =? and  table_name =?  ORDER BY ORDINAL_POSITION 
                    """;

    /**
     * 初始化数据库链接
     */
    private void init() {
        try {
            Class.forName(this.dbInfo.getDriverClassName());
            this.conn = DriverManager.getConnection(this.dbInfo.getUrl(), this.dbInfo.getUsername(), this.dbInfo.getPassword());
        } catch (Exception e) {
            log.warn("数据库连接失败{}", e.getMessage());
            throw new TzException("数据库连接失败" + e.getMessage());
        }
    }

    /**
     * 关闭数据库
     */
    public void close() {
        try {
            if (Objects.nonNull(this.conn)) {
                this.conn.close();
            }
        } catch (SQLException e) {
            log.warn("数据库关闭失败{}", e.getMessage());
        }
    }


    /**
     * 获取表信息
     * @param tableName
     * @return
     */
    private GenTable getTableInfoBase(String tableName,boolean close) {

        GenTable genTable = new GenTable();
        genTable.setTableName(tableName);

        try {
            if(this.conn.isClosed()){
                throw new TzException("连接已关闭{}");
            }

            PreparedStatement statement = this.conn.prepareStatement(SINGLE_TABLE_SQL);

            statement.setString(1, this.dbInfo.getDbName());
            statement.setString(2, tableName);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                GenTableFiled filed = new GenTableFiled(
                        rs.getString("COLUMN_NAME"),
                        rs.getString("DATA_TYPE"),
                        Objects.equals(rs.getString("COLUMN_KEY"), "PRI"),
                        Objects.equals(rs.getString("IS_NULLABLE"), "YES"),
                        rs.getString("COLUMN_COMMENT")
                );
                genTable.getFiledInfos().add(filed);
            }

            if(CollectionUtil.isEmpty(genTable.getFiledInfos())){
                throw new TzException("没有查询到数据");
            }

        } catch (SQLException e) {
            //如果报错就立刻关闭
            this.close();
            e.printStackTrace();
        }finally {
            if(close){
               this.close();
            }
        }
        return genTable;
    }




    /**
     * 获取表信息 ,自动关闭连接
     * @param tableNames
     * @return
     */
    public static List<GenTable> getTableInfos( DbInfo dbInfo, Set<String> tableNames) {

        List<GenTable> genTables = new ArrayList<>();
        GenDbUtil service = new GenDbUtil(dbInfo);

        tableNames.stream().filter(StrUtil::isNotBlank)
                .forEach(x-> genTables.add(service.getTableInfoBase(x,false)));
        service.close();

        return genTables;
    }
    public static List<GenTable> getTableInfos( DbInfo dbInfo, String... tableNames) {

        if(tableNames==null || tableNames.length==0){
            return List.of();
        }

        return getTableInfos(dbInfo ,Arrays.stream(tableNames).collect(Collectors.toSet()));
    }



    public static void main(String[] args) {
        DbInfo dbInfo = new DbInfo(
                "com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/ry-vue?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8",
                "root",
                "123123",
                "ry-vue"
        );

        List<GenTable> list = GenDbUtil.getTableInfos(dbInfo,"sys_dept");
        System.out.println(list);

    }

}