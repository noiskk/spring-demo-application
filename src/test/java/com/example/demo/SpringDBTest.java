package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringDBTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    void test_JPA_DB_Connection() {
        assertThat(entityManager).isNotNull();
        System.out.println("EntityManager 연결 성공: " + entityManager);
    }
}
