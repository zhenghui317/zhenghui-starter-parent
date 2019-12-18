package com.phenix.starter.mybatisplus.parameter;

import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GenerateCodeParameter {
    /**
     * 作者
     */
    private String author;
    /**
     * 生成代码命名空间
     */
    private  String nameSpace;
    /**
     * 数据库驱动类
     */
    private String driverClassName;
    /**
     * 数据库地址
     */
    private String dataSource;
    /**
     * 数据库用户名
     */
    private String dataUser;
    /**
     * 数据库密码
     */
    private String dataPwd;
    /**
     * 主键类型
     */
    private IdType idType;
    /**
     * 是否覆盖当前已有文件
     */
    private Boolean fileOverride;
    /**
     * 实体类父类
     */
    private String superEntityClass;
    /**
     * 控制器父类
     */
    private String superControllerClass;
    /**
     * Mapper父类
     */
    private String superMapperClass;
    /**
     * 服务父类
     */
    private String superServiceClass;
    /**
     * 服务实现父类
     */
    private String superServiceImplClass;
    /**
     * 逻辑删除字段名称
     */
    private String logicDeleteField;
    /**
     * 生成代码的表集合
     */
    private List<String> includeTableList;
    /**
     * 排除生成代码的表集合
     */
    private List<String> excludeTableList;
    /**
     * 生成代码中需要忽略的表前缀集合
     */
    private List<String> tablePrefixList;
}
