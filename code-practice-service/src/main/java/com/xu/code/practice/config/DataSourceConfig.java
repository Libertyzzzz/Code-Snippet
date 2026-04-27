package com.xu.code.practice.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


public class DataSourceConfig {

    /**
     * @Author liberty
     * @Date 2025/3/13 18:00
     */
    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .url("jdbc:mysql://localhost:3306/chappter01")
                .username("root")
                .password("123456")
                .build();
    }

}
