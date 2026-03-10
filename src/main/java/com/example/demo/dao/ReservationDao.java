package com.example.demo.dao;

import com.example.demo.model.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDao extends JpaRepository<Reservation, Integer> {
    // Spring Data JPA가 기본적인 CRUD 메서드를 자동으로 제공합니다.
    // 필요에 따라 여기에 추가적인 쿼리 메서드를 정의할 수 있습니다.
    // 예: List<Reservation> findByMemberId(Long memberId);
    // 예: List<Reservation> findByBookId(String bookId);
}
