package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.dao.ReservationDao;
import com.example.demo.dto.ReservationDto;
import com.example.demo.model.Member;
import com.example.demo.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDao reservationDao;
    private final MemberDao memberDao;
    // 설계도 이미지에 Book이 직접 연결되지 않고 rental_no가 있으므로 BookDao는 필요시 사용

    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        // 설계도에 따라 member_no(String)로 회원 조회
        Member member = Optional.ofNullable(memberDao.findByMemberNo(reservationDto.getMemberId())) // MemberDao의 PK 타입 확인 필요
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // 설계도 이미지의 3가지 필드(rental_no, member_no, reservation_seq) 위주로 빌드
        Reservation reservation = Reservation.builder()
                .rentalNo(reservationDto.getRentalNo())
                .member(member)
                // reservationSeq는 자동 생성(IDENTITY)이므로 넣지 않음
                .build();

        Reservation savedReservation = reservationDao.save(reservation);
        return toDto(savedReservation);
    }

    @Transactional(readOnly = true)
    public Optional<ReservationDto> getReservationById(int reservationSeq) {
        // ID 타입을 int(reservationSeq)로 변경
        return reservationDao.findById(reservationSeq).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public List<ReservationDto> getAllReservations() {
        return reservationDao.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteReservation(int reservationSeq) {
        reservationDao.deleteById(reservationSeq);
    }

    // Reservation 엔티티를 DTO로 변환 (설계도 필드 기준)
    private ReservationDto toDto(Reservation reservation) {
        return ReservationDto.builder()
                .reservationSeq(reservation.getReservationSeq())
                .rentalNo(reservation.getRentalNo())
                .memberId(reservation.getMember().getMemberNo()) // Member 엔티티의 ID 필드명 확인 필요
                .build();
    }
}