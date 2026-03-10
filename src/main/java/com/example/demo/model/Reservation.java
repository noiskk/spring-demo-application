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
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_seq") // 설계도의 '예약순번' (PK, int)
    private int reservationSeq;

    // 설계도: string / rental_no / FK / 대여번호
    // 실제 객체 지향 설계에서는 Rental 객체를 참조하지만,
    // 설계도 타입이 string이므로 아래와 같이 작성하거나 Rental 객체와 연결합니다.
    @Column(name = "rental_no", nullable = false)
    private String rentalNo;

    // 설계도: string / member_no / FK / 회원번호
    // Member 엔티티와 연관관계를 맺으면서 컬럼명만 member_no로 지정합니다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no", referencedColumnName = "member_id")
    private Member member;

    /* 참고: 만약 설계도대로 아주 단순하게 '문자열' 값만 저장하고 싶다면
       객체 참조 대신 아래처럼 쓸 수도 있습니다.
       @Column(name = "member_no")
       private String memberNo;
    */
}