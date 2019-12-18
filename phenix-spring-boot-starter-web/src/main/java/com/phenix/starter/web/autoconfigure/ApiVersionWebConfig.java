//package com.phenix.starter.web.autoconfigure;
//
//import com.phenix.starter.web.apiversion.CustomRequestMappingHandlerMapping;
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//
///**
// * 注册请求的版本注解配置类
// * @author lich
// *
// */
//@SpringBootConfiguration
//public class ApiVersionWebConfig extends WebMvcConfigurationSupport {
//
//    /**
//     * 注册请求的版本请求方法
//     */
//    @Override
//    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
//        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
//        handlerMapping.setOrder(0);
//        handlerMapping.setInterceptors(getInterceptors());
//        return handlerMapping;
//    }
//
//}
