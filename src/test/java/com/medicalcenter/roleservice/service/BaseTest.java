package com.medicalcenter.roleservice.service;

import com.medicalcenter.roleservice.service.containers.Postgres;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    static Postgres container = Postgres.getInstance();
    @DynamicPropertySource
    static void registerApplicationProperties(DynamicPropertyRegistry registry) {
        container.start();
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
