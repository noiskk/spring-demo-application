package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, String> {
    // 담당자 B가 BookDao를 재작성할 때, 여기에 필드 확장 및 categoryCode 기반 조회 추가와 같은
    // 추가적인 쿼리 메서드를 정의할 것입니다.
}
