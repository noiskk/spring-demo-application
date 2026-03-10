package com.example.demo.dto;

import lombok.*;

@Getter // 이게 있어야 getRentalNo()를 사용할 수 있습니다.
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private int reservationSeq; // 예약순번 (PK)
    private String rentalNo;    // 대여번호 (FK)
    private String memberId;    // 회원번호 (FK)

    // 혹시 기존에 있던 아래 필드들은 설계도에 없으므로 주석 처리하거나 삭제해도 됩니다.
    // private LocalDateTime reservationDate;
    // private String status;
}