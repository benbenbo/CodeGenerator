package com.zby.service;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zby.web.model.request.CodeGenerateRequest;
import org.springframework.stereotype.Service;

/**
 * @author XBird
 * @date 2021/8/2
 **/
@Service
public class CodeGenerateService {
    public void execute(CodeGenerateRequest request, String outputDir) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = getGlobalConfig(request.getAuthor(), outputDir);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        PackageConfig pc = getPackageConfig(request.getParentPackage(), request.getModuleName(), request.getServiceName(), request.getModelPackage(), request.getMapperPackage(), request.getMapperXmlPackage());
        mpg.setPackageInfo(pc);

        StrategyConfig strategyConfig = getStrategyConfig(request.getTablePrefix(), request.getTableNames());
        mpg.setStrategy(strategyConfig);


        DataSourceConfig dataSourceConfig = getDataSourceConfig(request.getJdbcUrl(), request.getDriverName(), request.getUserName(), request.getPassword());
        mpg.setDataSource(dataSourceConfig);
        mpg.execute();

    }

    private static GlobalConfig getGlobalConfig(String author, String outputDir) {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setAuthor(author);
        gc.setFileOverride(true);
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sService");
        gc.setOpen(false);
        return gc;
    }


    private static PackageConfig getPackageConfig(String parentPackage, String moduleName, String servicePackage, String modelPackage, String mapperPackage, String mapperXmlPackage) {
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(parentPackage);
        pc.setService(servicePackage);
        pc.setServiceImpl(servicePackage);
        pc.setEntity(modelPackage);
        pc.setMapper(mapperPackage);
        pc.setXml(mapperXmlPackage);
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
