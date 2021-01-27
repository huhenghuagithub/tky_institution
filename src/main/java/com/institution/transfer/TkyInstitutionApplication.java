package com.institution.transfer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication()
@MapperScan({"com.institution.transfer.dao.mapper"})
/** 开启事务支持 */
@EnableTransactionManagement
public class TkyInstitutionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TkyInstitutionApplication.class, args);
    }

}
