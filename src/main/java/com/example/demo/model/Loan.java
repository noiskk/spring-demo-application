package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Loan {
    private Long id;            // 대출/예약 식별 번호
    private String username;    // 예약자 아이디
    private Book book;          // 예약된 도서 객체
    private LocalDateTime loanDate; // 예약(대출) 일시
}