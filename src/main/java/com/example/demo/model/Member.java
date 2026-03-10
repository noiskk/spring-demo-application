package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name = "member_no")
    private String memberNo;    // 회원번호 (PK)

    @Column(name = "login_id", unique = true)
    private String loginId;     // 로그인 아이디

    private String password;    // 비밀번호

    @Column(name = "member_name")
    private String memberName;  // 회원명

    @Column(name = "resident_no")
    private String residentNo;  // 주민번호

    private String address;     // 주소
    private String phone;       // 전화번호
    private String email;       // e-mail

    @Column(name = "overdue_fee", columnDefinition = "integer default 0")
    private int overdueFee;     // 연체료
}
