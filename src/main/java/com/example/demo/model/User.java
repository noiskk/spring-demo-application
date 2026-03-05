package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {

    private Long id;          // 식별자
    private String username;  // 로그인 아이디
    private String password;  // 비밀번호

}
