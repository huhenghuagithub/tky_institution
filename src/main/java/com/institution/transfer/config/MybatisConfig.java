package com.institution.transfer.config;


import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    /**
     * mybatis 自定义拦截器
     */
    @Bean
    public Interceptor getInterceptor(){
        return new MyBatisInterceptor();
    }

}
