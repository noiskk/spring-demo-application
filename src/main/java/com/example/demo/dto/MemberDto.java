package com.example.demo.dto;

import com.example.demo.model.Member;
import lombok.Getter;

@Getter
public class MemberDto {

    private String memberNo;    // 회원번호
    private String memberName;  // 회원명
    private String address;     // 주소
    private String phone;       // 전화번호
    private String email;       // e-mail
    private int overdueFee;     // 연체료

    private MemberDto(Member member) {
        this.memberNo   = member.getMemberNo();
        this.memberName = member.getMemberName();
        this.address    = member.getAddress();
        this.phone      = member.getPhone();
        this.email      = member.getEmail();
        this.overdueFee = member.getOverdueFee();
    }

    public static MemberDto from(Member member) {
        return new MemberDto(member);
    }
}
