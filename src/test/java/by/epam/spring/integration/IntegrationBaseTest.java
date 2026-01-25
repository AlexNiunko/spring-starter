package by.epam.spring.integration;

import by.epam.spring.integration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
@Sql(scripts = {"classpath:sql/data.sql"})
@WithMockUser(username="test@gmail.com",password = "test",authorities = {"ADMIN","USER"})
public abstract class IntegrationBaseTest {

    private static final PostgreSQLContainer<?> container=new PostgreSQLContainer<>("postgres:14.1");

    @BeforeAll
    static void runContainer(){
        container.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry. add("spring.datasource.url", container::getJdbcUrl);
    }

}
