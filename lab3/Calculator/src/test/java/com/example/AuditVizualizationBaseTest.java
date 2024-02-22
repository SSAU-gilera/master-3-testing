package com.example;

import com.example.Calculator.OperationEntity;
import org.flywaydb.core.Flyway;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.*;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;


@Testcontainers
@ContextConfiguration(initializers = {AuditVizualizationBaseTest.Initializer.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class, SqlScriptsTestExecutionListener.class})
public abstract class AuditVizualizationBaseTest {

    static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

    static {
        POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:15.2")
                .withDatabaseName("calculator_db")
                .withUsername("postgres")
                .withPassword("1111");
        POSTGRE_SQL_CONTAINER.start();

        Flyway.configure()
                .dataSource(POSTGRE_SQL_CONTAINER.getJdbcUrl(), POSTGRE_SQL_CONTAINER.getUsername(), POSTGRE_SQL_CONTAINER.getPassword())
                .cleanDisabled(false)
                .load()
                .migrate();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "database.cache.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    "database.cache.username=" + POSTGRE_SQL_CONTAINER.getUsername(),
                    "database.cache.password=" + POSTGRE_SQL_CONTAINER.getPassword(),
                    "spring.flyway.url=" + POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                    "spring.flyway.user=" + POSTGRE_SQL_CONTAINER.getUsername(),
                    "spring.flyway.password=" + POSTGRE_SQL_CONTAINER.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}