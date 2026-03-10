package com.example.demo.service;

import com.example.demo.dao.LoanDao;
import com.example.demo.model.Book;
import com.example.demo.model.Loan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanDao loanDao;

    // 예약 내역 추가
    public void addLoan(String username, Book book) {
        Loan loan = new Loan(null, username, book, LocalDateTime.now());
        loanDao.save(loan);
    }

    // 내 예약 목록 가져오기
    public List<Loan> getMyLoans(String username) {
        return loanDao.findByUsername(username);
    }

    // 예약 취소 시 내역 제거
    public void removeLoan(String bookId) { // Long -> String으로 변경
        loanDao.deleteByBookId(bookId); // LoanDao의 deleteByBookId도 String을 받도록 변경 필요
    }
}