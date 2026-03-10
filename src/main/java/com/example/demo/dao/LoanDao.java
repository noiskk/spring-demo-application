package com.example.demo.dao;

import com.example.demo.model.Loan;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LoanDao {
    private static final List<Loan> loanDatabase = new ArrayList<>();
    private static Long sequence = 1L;

    // 예약 내역 저장
    public void save(Loan loan) {
        loan.setId(sequence++);
        loanDatabase.add(loan);
    }

    // 특정 사용자의 예약 내역 조회
    public List<Loan> findByUsername(String username) {
        return loanDatabase.stream()
                .filter(loan -> loan.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    // 예약 취소 시 내역 삭제
    public void deleteByBookId(String bookId) { // Long -> String으로 변경
        loanDatabase.removeIf(loan -> loan.getBook().getId().equals(bookId));
    }
}