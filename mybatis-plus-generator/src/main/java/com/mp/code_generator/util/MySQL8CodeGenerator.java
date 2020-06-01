package com.mp.code_generator.util;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @Fun Description //TODO
 * @Date 2020/6/1 11:19 01
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class MySQL8CodeGenerator {
    public static void main(String[] args) {
        DbType dbType = DbType.MYSQL;
        String dbUrl = "jdbc:mysql://localhost:27706/mplus";
        String username = "root";
        String password = "doctor330_2016Brenda";
        String driver = "com.mysql.cj.jdbc.Driver";
        // 表前缀，生成的实体类，不含前缀
        String [] tablePrefixes = {};
        // 表名，为空，生成所有的表
        String [] tableNames = {"employee"};
        // 字段前缀
        String [] fieldPrefixes = {};
        // 基础包名
        String packageName = "com.mp.module.db";
        CommonUtils.execute(dbType, dbUrl, username, password, driver, tablePrefixes, tableNames, packageName, fieldPrefixes);
    }
}
