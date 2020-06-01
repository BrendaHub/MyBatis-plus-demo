package com.mp.code_generator.util;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * @Fun Description //TODO
 * @Date 2020/6/1 11:20 01
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class OracleCodeGenerator {
    public static void main(String[] args) {
        DbType dbType = DbType.ORACLE;
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "dbUsername";
        String password = "dbPassword";
        String driver = "oracle.jdbc.OracleDriver";
        // 表前缀，生成的实体类，不含前缀
        String [] tablePrefixes = {};
        // 表名，为空，生成所有的表
        String [] tableNames = {};
        // 字段前缀
        String [] fieldPrefixes = {};
        // 基础包名
        String packageName = "com.example.module.db";
        CommonUtils.execute(dbType, dbUrl, username, password, driver, tablePrefixes, tableNames, packageName, fieldPrefixes);
    }
}
