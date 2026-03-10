package com.example.demo.service;

import com.example.demo.dao.BookDao; // Assuming BookDao will be created by 담당자 B
import com.example.demo.dao.MemberDao; // Assuming MemberDao will be created by 담당자 A
import com.example.demo.dao.ReservationDao;
import com.example.demo.controller.dto.ReservationDto;
import com.example.demo.model.Book;
import com.example.demo.model.Member;
import com.example.demo.model.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Lombok을 사용하여 final 필드를 가진 생성자 자동 생성 (의존성 주입)
public class ReservationService {

    private final ReservationDao reservationDao;
    private final MemberDao memberDao; // 담당자 A가 생성할 MemberDao 의존성
    private final BookDao bookDao;   // 담당자 B가 생성할 BookDao 의존성

    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        // DTO에서 Member 및 Book 엔티티 조회 (담당자 A, B의 구현 가정)
        Member member = memberDao.findById(reservationDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        Book book = bookDao.findById(reservationDto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Reservation reservation = Reservation.builder()
                .member(member)
                .book(book)
                .reservationDate(reservationDto.getReservationDate())
                .status(reservationDto.getStatus())
                .build();

        Reservation savedReservation = reservationDao.save(reservation);
        return toDto(savedReservation);
    }

    @Transactional(readOnly = true)
    public Optional<ReservationDto> getReservationById(Long id) {
        return reservationDao.findById(id).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public List<ReservationDto> getAllReservations() {
        return reservationDao.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Reservation reservation = reservationDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        // Member 및 Book 엔티티 업데이트 (ID가 변경될 경우)
        if (!reservation.getMember().getId().equals(reservationDto.getMemberId())) {
            Member newMember = memberDao.findById(reservationDto.getMemberId())
                    .orElseThrow(() -> new IllegalArgumentException("Member not found"));
            reservation.setMember(newMember);
        }
        if (!reservation.getBook().getId().equals(reservationDto.getBookId())) {
            Book newBook = bookDao.findById(reservationDto.getBookId())
                    .orElseThrow(() -> new IllegalArgumentException("Book not found"));
            reservation.setBook(newBook);
        }

        reservation.setReservationDate(reservationDto.getReservationDate());
        reservation.setStatus(reservationDto.getStatus());

        Reservation updatedReservation = reservationDao.save(reservation);
        return toDto(updatedReservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        reservationDao.deleteById(id);
    }

    // Reservation 엔티티를 ReservationDto로 변환하는 헬퍼 메서드
    private ReservationDto toDto(Reservation reservation) {
        return ReservationDto.builder()
                .id(reservation.getId())
                .memberId(reservation.getMember().getId())
                .bookId(reservation.getBook().getId())
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus())
                .build();
    }
}
