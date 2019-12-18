# phenix-spring-boot-starter说明


> 制定starter包目标为，约束统一的开发组件版本，减少应用代码的配置内容。

> 当前所有版本为1.0.0-SNAPSHOT，后续稳定将切换为1.0.0.RELEASE。

> starter包保存在阿里云私有仓库中,开通阿里云效后可在私有仓库中找到，开发需要下载settings.xml，并覆盖本地Maven的settings.xml才能拉到。

> 除phenix-spring-boot-starter-parent外，其余starter包为选配，可根据自己应用实际需要添加。
## phenix-spring-boot-starter-parent

starter基础包，当前选用SpringBoot 2.1.9.RELEASE版本，SpringCloud Greenwich.SR3版本。

> GAV
```xml
   <parent>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-parent</artifactId>
      <version>1.0.0-SNAPSHOT</version>
   </parent>
```

## phenix-spring-boot-starter-admin

   用于spring-boot-admin的starter包，主要用于管理当前应用状态和详情。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-admin</artifactId>
   </dependency>
```
## phenix-spring-boot-starter-feign

   主要用于采用feign方式实现服务间通信。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-feign</artifactId>
   </dependency>
```

## phenix-spring-boot-starter-logging

   主要用于默认日志配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-logging</artifactId>
   </dependency>
```
## phenix-spring-boot-starter-mybatis-plus

   主要用于mybatis-plus的默认配置，以及自定义代码生成器与多租户配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-mybatis-plus</artifactId>
   </dependency>
```
## phenix-spring-boot-starter-mysql

   主要用于mysql的默认配置，以及自动解密数据源配置中加密的账号密码。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-mysql</artifactId>
   </dependency>
```

## phenix-spring-boot-starter-nacos

   主要用于nacos的默认配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-nacos</artifactId>
   </dependency>
```
Nacos需要在应用中需配置config和discovery地址，以及配置文件所需data-id，示例配置如下：

```yaml
spring:
  application:
    name: mcs
  profiles:
    active: dev
  cloud:
    nacos:
      config:
        enabled: true
        file-extension: yaml
        ext-config[0].data-id: application-${spring.profiles.active}.${spring.cloud.nacos.config.file-extension}
        ext-config[0].refresh: true


---
spring:
  profiles: dev
  cloud:
    nacos:
      config:
        server-addr: 192.168.0.201:8848
        namespace: ecab5663-eae7-44a9-8e1c-bf0015bf9b04
      discovery:
        server-addr: 192.168.0.201:8848
        namespace: ecab5663-eae7-44a9-8e1c-bf0015bf9b04

```

## phenix-spring-boot-starter-rabbitmq

   主要用于rabbitmq的默认配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-rabbitmq</artifactId>
   </dependency>
```

## phenix-spring-boot-starter-redis

   主要用于redis的默认配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-redis</artifactId>
   </dependency>
```

## phenix-spring-boot-starter-swagger

   主要用于swagger的默认配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-swagger</artifactId>
   </dependency>
```


## phenix-spring-boot-starter-web

   主要用于web的默认配置。

> GAV
```xml
   <dependency>
      <groupId>com.phenix</groupId>
      <artifactId>phenix-spring-boot-starter-web</artifactId>
   </dependency>
```
> 注意，当前web是基于springmvc，如果使用webflux不要引用此包，有可能引发错误，例如Spring Cloud Gateway.
