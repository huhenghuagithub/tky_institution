package com.institution.transfer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration //在springboot中加载配置文件
@EnableSwagger2 //加载swagger
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("接口API","数据迁移接口")) // 调用apiInfo方法
                .pathMapping("/") //配置访问路径
                .select()
                .paths(PathSelectors.regex("/.*")) //匹配路径下的方法
                .build();

    }

    private ApiInfo apiInfo(String title, String subtitle){
        return new ApiInfoBuilder().title(title)
                .contact(new Contact("institution","", "1643708127@qq.com"))
                .description(subtitle)
                .version("1.0")
                .build();

    }

}
