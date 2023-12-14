package com.github.zouzdc.gen.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Connection;

/**
 * @author ZDC
 * @Description 数据库连接信息
 * @Version 1.0.0
 * @Date 2023/12/5 21:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbInfo implements Serializable {

    /**
     * 驱动类
     */
    private String driverClassName;
    /**
     * 连接地址
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    /**
     * 数据库名称
     */
    private String dbName;



}