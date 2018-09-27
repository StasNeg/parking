package com.example.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Order(10)
@ConditionalOnProperty(name = "app.db-init", havingValue = "true")
public class DataSoursePopulatorImpl implements ApplicationRunner {

    private DataSource dataSource;

    @Autowired
    public DataSoursePopulatorImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Resource initSchema = new ClassPathResource("scripts/schema.sql");
        Resource initData = new ClassPathResource("scripts/data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(true, true, null,initData);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }
}
