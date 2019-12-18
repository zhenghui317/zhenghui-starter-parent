package com.phenix.starter.swagger.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chihiro.zhang
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {

    private Boolean enabled;

    private String title;

    private String description;

    private String webBasePackage;

    private String author;

    private String url;

    private String email;

    private boolean redirect = false;
}

