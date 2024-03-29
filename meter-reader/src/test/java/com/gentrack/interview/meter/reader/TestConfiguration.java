package com.gentrack.interview.meter.reader;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

@Configuration
public class TestConfiguration {

    @Autowired
    @Qualifier("postgreSQLContainer")
    PostgreSQLContainer postgreSQLContainer;

    @Primary
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(org.postgresql.Driver.class.getName())
                .password(postgreSQLContainer.getPassword())
                .username(postgreSQLContainer.getUsername())
                .url("jdbc:postgresql://localhost:" + postgreSQLContainer.getMappedPort(POSTGRESQL_PORT) + "/meterreader?currentschema=meter")
                .build();
    }

}
