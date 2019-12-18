package com.phenix.starter.mybatisplus.autoconfigure;

import com.phenix.starter.mybatisplus.datasource.UmspscDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 配置数据源
 * @author phenix
 * @date  2019-11-19
 */
@Configuration
public class PhenixDataSourceConfiguration {

    @Bean(name = "dataSource", autowire = Autowire.NO)
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public HikariDataSource dataSource() {
        HikariDataSource druidDataSource = new UmspscDataSource();
        return druidDataSource;
    }

}
