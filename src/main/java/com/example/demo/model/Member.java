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
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 담당자 A의 Member 기반 재작성 지시를 따름

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // 실제 앱에서는 인코딩 필요

    @Column(nullable = false)
    private String name;

    // TODO: Add other necessary fields (e.g., email, phone, role) as per 담당자 A
}