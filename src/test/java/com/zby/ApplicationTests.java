package com.zby;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author XBird
 * @date 2021/7/21
 **/
@RunWith(JUnit4.class)
public class ApplicationTests {
    @Test
    public void test() {
        generator();
    }

    public static void generator() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/eblog?useUnicode=true&useSSL=false&characterEncoding=utf8";
        String driverName = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "123456";
        String tablePrefix = "m_";
        String tableNames = "m_user";
        String author = "zhengbingyuan";
        String moduleName = "module";
        String parentPackage = "com.baomidou.ant";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/temp/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA)));
        gc.setAuthor(author);
        gc.setFileOverride(true);
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sService");
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig(jdbcUrl, driverName, userName, password);
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = getPackageConfig(parentPackage, moduleName);
        mpg.setPackageInfo(pc);
        // 策略配置
        StrategyConfig strategy = getStrategyConfig(tablePrefix, tableNames);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

    /**
     * 包名配置
     *
     * @return PackageConfig对象
     */
    private static PackageConfig getPackageConfig(String parentPackage, String moduleName) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(parentPackage);
        pc.setService("service");
        pc.setServiceImpl("service");
        return pc;
    }

    private static StrategyConfig getStrategyConfig(String tablePrefix, String tableNames) {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl");
        strategy.setInclude(tableNames);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);
        return strategy;
    }

    private static DataSourceConfig getDataSourceConfig(String jdbcUrl, String driverName, String userName, String password) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(jdbcUrl);
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        return dsc;
    }

}

