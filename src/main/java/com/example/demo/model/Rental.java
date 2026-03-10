package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    private String rental_no;   // 대여번호 PK
    private String book_no;     // 도서번호 FK
    private String member_no;   // 회원번호 FK
    private LocalDate rental_date; // 대출일자
    private LocalDate return_date; // 반납일자
    private boolean extension; // 연장
}