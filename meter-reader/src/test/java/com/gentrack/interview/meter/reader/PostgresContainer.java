package com.gentrack.interview.meter.reader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

@Configuration
public class PostgresContainer {

    @Bean(name = "postgreSQLContainer", destroyMethod = "stop")
    @Primary
    public PostgreSQLContainer postgresSQLContainer() {
        PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:10.3")
                .withDatabaseName("meterreader")
                .withUsername("meter")
                .withPassword("meter");
        postgreSQLContainer.waitingFor(forLogMessage("(?ms).*PostgreSQL init process complete.*", 1));
        postgreSQLContainer.start();
        return postgreSQLContainer;
    }
}
