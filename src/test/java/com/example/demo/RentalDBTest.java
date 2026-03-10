package com.example.demo;

import com.example.demo.model.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RentalDBTest {

    EntityManagerFactory emf;
    EntityManager em;
    EntityTransaction tx;

    @BeforeEach
    void setUp() {
        emf = new Configuration()
                .setProperty("hibernate.connection.driver_class",
                        "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url",
                        "jdbc:mysql://localhost:3306/testdb?serverTimezone=UTC")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "zion000228")
                .setProperty("hibernate.hbm2ddl.auto", "none")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .addAnnotatedClass(Rental.class)
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
    @DisplayName("Rental 조회 테스트")
    void testFindRental() {
        Rental rental = em.find(Rental.class, "R001");
        System.out.println("rental_no = " + rental.getRental_no());
        System.out.println("book_no = " + rental.getBook_no());
        System.out.println("member_no = " + rental.getMember_no());
        System.out.println("rental_date = " + rental.getRental_date());
        assertThat(rental).isNotNull();
        assertThat(rental.getBook_no()).isEqualTo("B001");
    }

    @Test
    @DisplayName("Rental 저장 테스트")
    void testSaveRental() {
        tx.begin();

        Rental rental = new Rental("R004", "B004", "M004",
                LocalDate.now(), null, false);
        em.persist(rental);

        tx.commit();

        Rental found = em.find(Rental.class, "R004");
        assertThat(found).isNotNull();
        assertThat(found.getMember_no()).isEqualTo("M004");
    }

    @Test
    @DisplayName("회원번호로 대출 목록 조회")
    void testFindByMemberNo() {
        List<Rental> rentals = em.createQuery(
                        "SELECT r FROM Rental r WHERE r.member_no = :memberNo", Rental.class)
                .setParameter("memberNo", "M001")
                .getResultList();

        System.out.println("M001 대출 건수 = " + rentals.size());
        assertThat(rentals).isNotEmpty();
    }

    @Test
    @DisplayName("반납 처리 테스트")
    void testReturnRental() {
        tx.begin();

        Rental rental = em.find(Rental.class, "R001");
        rental.setReturn_date(LocalDate.now());

        tx.commit();

        Rental updated = em.find(Rental.class, "R001");
        assertThat(updated.getReturn_date()).isNotNull();
    }

    @Test
    @DisplayName("연장 처리 테스트")
    void testExtendRental() {
        tx.begin();

        Rental rental = em.find(Rental.class, "R001");
        rental.setExtension(true);

        tx.commit();

        Rental updated = em.find(Rental.class, "R001");
        assertThat(updated.isExtension()).isTrue();
    }
}