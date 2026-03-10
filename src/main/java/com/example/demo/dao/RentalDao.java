package com.example.demo.dao;

import com.example.demo.model.Rental;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RentalDao {

    @PersistenceContext
    private EntityManager manager;


    // 대출 저장
    @Transactional
    public void save(Rental rental) {

        manager.persist(rental);

    }

    // 대출번호로 조회
    public Optional<Rental> findById(String rentalNo) {
        Rental rental = manager.find(Rental.class, rentalNo);
        return Optional.ofNullable(rental);
    }

    // 회원번호로 조회
    // PK 아닌 조건 조회 → JPQL 필수
    public List<Rental> findByMemberNo(String memberNo) {
        return manager.createQuery(
                        "SELECT r FROM Rental r WHERE r.member_no = :memberNo", Rental.class)
                .setParameter("memberNo", memberNo)
                .getResultList();
    }

    // 도서번호로 조회
    public List<Rental> findByBookNo(String bookNo) {
        return manager.createQuery(
                        "SELECT r FROM Rental r WHERE r.book_no = :bookNo", Rental.class)
                .setParameter("bookNo", bookNo)
                .getResultList();
    }

    // 대출 삭제
    @Transactional
    public void deleteByRentalNo(String rentalNo) {
        Rental rental = manager.find(Rental.class, rentalNo);
        if (rental != null) {
            manager.remove(rental);

        }
    }
}