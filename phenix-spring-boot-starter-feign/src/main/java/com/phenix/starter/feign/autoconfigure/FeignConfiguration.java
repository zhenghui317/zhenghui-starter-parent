package com.phenix.starter.feign.autoconfigure;

import feign.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 配置
 * @author phenix
 * @date 2019/11/28
 */
@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.phenix.feignapi.*")
public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }
}
