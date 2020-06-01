package com.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Driver;

/**
 * @Fun Description //TODO
 * @Date 2020/5/31 11:21 31
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
@SpringBootTest
public class CodeGeneratorTest {
    /**
     * 是否强制带上注解
     */
    private boolean enableTableFieldAnnotation = false;
    /**
     * 生成的注解带上IdType类型
     */
    private IdType tableIdType = null;
    /**
     * 是否去掉生成实体的属性名前缀
     */
    private String[] fieldPrefix = null;
    /**
     * 生成的Service 接口类名是否以I开头
     * <p>默认是以I开头</p>
     * <p>user表 -> IUserService, UserServiceImpl</p>
     */
    private boolean serviceClassNameStartWithI = true;

    @Test
    public void generateCode() {
        String packageName = "com.mp.springboot";
        enableTableFieldAnnotation = false;
        tableIdType = null;
        generateByTables(packageName + ".noannoidtype", "user");
        enableTableFieldAnnotation = true;
        tableIdType = null;
        generateByTables(packageName + ".noidtype", "user");
        enableTableFieldAnnotation = false;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".noanno", "user");
        enableTableFieldAnnotation = true;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".both", "user");

        fieldPrefix = new String[]{"test"};
        enableTableFieldAnnotation = false;
        tableIdType = null;
        generateByTables(packageName + ".noannoidtypewithprefix", "user");
        enableTableFieldAnnotation = true;
        tableIdType = null;
        generateByTables(packageName + ".noidtypewithprefix", "user");
        enableTableFieldAnnotation = false;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".noannowithprefix", "user");
        enableTableFieldAnnotation = true;
        tableIdType = IdType.INPUT;
        generateByTables(packageName + ".withannoidtypeprefix", "user");

        serviceClassNameStartWithI = false;
        generateByTables(packageName, "user");
    }

    private void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "jdbc:h2:tcp://localhost/~/mp_db";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("sa")
                .setPassword("sa")
                .setDriverName(Driver.class.getName());
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                // .setDbColumnUnderline(true) 改为如下 2 个配置
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(enableTableFieldAnnotation)
                .setFieldPrefix(fieldPrefix)//test_id -> id, test_type -> type
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setIdType(tableIdType)
                .setAuthor("K神带你飞")
                .setOutputDir("d:\\codeGen")
                .setFileOverride(true);
        if (!serviceClassNameStartWithI) {
            config.setServiceName("%sService");
        }
        new AutoGenerator_bak().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setEntity("entity")
                ).execute();
    }
}
