package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Book {
    private Long id;            // 도서 식별 번호
    private String title;       // 도서 제목
    private String author;      // 저자
    private boolean isReserved; // 예약 여부 (true: 예약됨, false: 예약 가능)
}