package com.example.demo;

import com.example.demo.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DBTest {

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    @BeforeEach
    void setUp() {
        emf = new Configuration()
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "1234")
                .setProperty("hibernate.hbm2ddl.auto", "none")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .addAnnotatedClass(Member.class)
                .buildSessionFactory();

        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    @DisplayName("Member Entity test")
    void testMemberFind() {
        Member m = em.find(Member.class, "M001");
        System.out.println("m = " + m);
    }
}
