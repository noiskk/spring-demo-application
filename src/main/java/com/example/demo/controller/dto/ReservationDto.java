package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private Long memberId;
    private String bookId;
    private LocalDateTime reservationDate;
    private String status;

    // TODO: Add other fields if Reservation model expands
}
