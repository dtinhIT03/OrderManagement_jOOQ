package org.example.ordermanagement_jooq.config;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;

@Configuration
public class JOOQConfig {
    @Bean
    public DSLContext dslContext(DataSource dataSource){
        return DSL.using(DataSourceUtils.getConnection(dataSource), SQLDialect.POSTGRES);
    }
}
