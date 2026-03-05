package com.example.demo.dao;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDao {
    // 메모리 데이터베이스
    private static final List<Book> bookDatabase = new ArrayList<>();

    // 클래스 로딩 시 초기 데이터 삽입
    static {
        bookDatabase.add(new Book(1L, "스프링 부트 실전", "김철수", false));
        bookDatabase.add(new Book(2L, "타임리프 완벽 가이드", "이영희", false));
        bookDatabase.add(new Book(3L, "자바 마스터", "박지성", false));
        bookDatabase.add(new Book(4L, "객체지향의 사실과 오해", "조영호", false));
    }

    /**
     * 모든 도서 목록 조회
     */
    public List<Book> findAll() {
        return bookDatabase;
    }

    /**
     * ID로 특정 도서 조회
     */
    public Optional<Book> findById(Long id) {
        return bookDatabase.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    /**
     * 도서 상태 업데이트 (예약/취소 시 호출)
     */
    public void update(Book updatedBook) {
        findById(updatedBook.getId()).ifPresent(book -> {
            book.setReserved(updatedBook.isReserved());
        });
    }
}