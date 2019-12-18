//package com.phenix.starter.nacos.autoconfigure;
//
//import com.alibaba.nacos.api.annotation.NacosProperties;
//import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
//import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySources;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableNacosConfig(globalProperties = @NacosProperties(serverAddr = "${spring.cloud.nacos.config.server-addr}"))
//@NacosPropertySources({
//        @NacosPropertySource(dataId = "application-"+"${spring.profiles.active}"+".yaml",  autoRefreshed = true),
//        @NacosPropertySource(dataId = "${spring.application.name}"+"-"+"${spring.profiles.active}"+".yaml",  autoRefreshed = true),
//})
//public class NacosConfiguration {
//}