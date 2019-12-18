package com.phenix.starter.mybatisplus.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * 代码生成器
 *
 * @author phenix
 * @date 2019-12-06
 */
public class MybatisPlusCodeGenerator {

    private String logicDeleteField = "is_delete";
    /**
     * 生成代码中需要忽略的表前缀集合
     */
    private List<String> tablePrefixList = new ArrayList<String>() {{
        add("s_");
        add("t_");
        add("l_");
    }};

    /**
     * 生成代码
     *
     * @param author       作者
     * @param nameSpace    生成代码命名空间
     * @param dataSource   数据库地址
     * @param dataUser     数据库用户名
     * @param dataPwd      数据库密码
     * @param idType       主键类型
     * @param fileOverride 是否覆盖当前已有文件
     */
    public void generate(String author, String nameSpace, String dataSource, String dataUser, String dataPwd,
                         IdType idType, Boolean fileOverride) {
        this.generate(author, nameSpace, dataSource, dataUser, dataPwd, idType, fileOverride, this.logicDeleteField, null, null, this.tablePrefixList);
    }

    /**
     * 生成代码
     *
     * @param author       作者
     * @param nameSpace    生成代码命名空间
     * @param dataSource   数据库地址
     * @param dataUser     数据库用户名
     * @param dataPwd      数据库密码
     * @param idType       主键类型
     * @param fileOverride 是否覆盖当前已有文件
     * @param logicDeleteField 逻辑删除字段名称
     */
    public void generate(String author, String nameSpace, String dataSource, String dataUser, String dataPwd,
                         IdType idType, Boolean fileOverride, String logicDeleteField) {
        this.generate(author, nameSpace, dataSource, dataUser, dataPwd, idType, fileOverride, logicDeleteField, null, null, this.tablePrefixList);
    }

    /**
     * 生成代码
     *
     * @param author           作者
     * @param nameSpace        生成代码命名空间
     * @param dataSource       数据库地址
     * @param dataUser         数据库用户名
     * @param dataPwd          数据库密码
     * @param fileOverride     是否覆盖当前已有文件
     * @param logicDeleteField 逻辑删除字段名称
     * @param includeTableList 生成代码的表集合
     * @param excludeTableList 排除生成代码的表集合
     * @param tablePrefixList  生成代码中需要忽略的表前缀集合
     */
    public void generate(String author, String nameSpace, String dataSource, String dataUser, String dataPwd,
                         IdType idType, Boolean fileOverride, String logicDeleteField, List<String> includeTableList,
                         List<String> excludeTableList, List<String> tablePrefixList) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setSwagger2(true);
        //覆盖文件
        gc.setFileOverride(fileOverride);
        gc.setIdType(idType);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSource);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(dataUser);
        dsc.setPassword(dataPwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));

        /**
         * 生成的packages存放的目录
         */
        pc.setParent(nameSpace);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                /**
                 * 自定义输入文件名称(*Mapper.xml存放路径)
                 */
                return projectPath + "/src/main/resources/mapping/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        //模板配置
        TemplateConfig templateConfig = new TemplateConfig()
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                .setEntity("/templates/entity.java")
                .setController("/templates/controller.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        /**
         * 需要生成代码的表集合
         */
        if (includeTableList != null && includeTableList.size() > 0) {
            String[] includeTableArray = new String[includeTableList.size()];
            includeTableList.toArray(includeTableArray);
            strategy.setInclude(includeTableArray);
        }

        /**
         * 排除生成代码的表集合
         */
        if (excludeTableList != null && excludeTableList.size() > 0) {
            String[] excludeTableArray = new String[excludeTableList.size()];
            excludeTableList.toArray(excludeTableArray);
            strategy.setExclude(excludeTableArray);
        }

        strategy.setControllerMappingHyphenStyle(false);
        if (tablePrefixList == null || tablePrefixList.size() == 0) {
            tablePrefixList = this.tablePrefixList;
        }
        String[] tablePrefixArray = new String[tablePrefixList.size()];
        tablePrefixList.toArray(tablePrefixArray);
        //生成代码中需要忽略的表前缀
        strategy.setTablePrefix(tablePrefixArray);
        if (logicDeleteField == null) {
            logicDeleteField = this.logicDeleteField;
        }
        //标识逻辑删除的字段名
        strategy.setLogicDeleteFieldName(logicDeleteField);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
