package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    private String id; // 담당자 B의 Book String PK 지시를 따름

    @Column(nullable = false)
    private String title;

    private boolean reserved; // BookService에서 사용하는 reserved 필드 추가

    // TODO: Add other necessary fields (e.g., author, publisher, category, ISBN, publicationDate, stockQuantity) as per 담당자 B
    // TODO: Field expansion and categoryCode based lookup for BookDao
}