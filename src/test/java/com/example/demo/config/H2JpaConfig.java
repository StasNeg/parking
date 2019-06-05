package com.example.demo.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@TestConfiguration
@EnableTransactionManagement
public class H2JpaConfig {
//    @Bean
//    public DataSource dataSource() {
//        return Mockito.mock(DataSource.class);
//    }
}