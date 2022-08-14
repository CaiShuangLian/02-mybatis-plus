package com.mp.demo.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @Author:CaiShuangLian
 * @FileName:
 * @Date:Created in  2022/7/26 13:04
 * @Version:
 * @Description: swagger配置
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    // 创建Docket存入容器，Docket代表一个接口文档
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                // 创建接口文档的具体信息
                .apiInfo(webApiInfo())
                // 创建选择器，控制哪些接口被加入文档
                .select()
                // 指定@ApiOperation标注的接口被加入文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    // 创建接口文档的具体信息，会显示在接口文档页面中
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                // 文档标题
                .title("标题：会议室预定demo")
                // 文档描述
                .description("描述：会议室预定demo")
                // 版本
                .version("1.0")
                // 联系人信息
                .contact(new Contact("csl", "http://csl.com", "csl@163.com"))
                // 版权
                .license("csl")
                // 版权地址
                .licenseUrl("http://www.csl.com")
                .build();
    }
}
