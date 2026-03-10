package com.example.demo.service;

import com.example.demo.dao.RentalDao;
import com.example.demo.dto.RentalDto;
import com.example.demo.model.Member;
import com.example.demo.model.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {

    private final RentalDao rentalDao;

    @PersistenceContext
    private EntityManager em;

    public RentalService(RentalDao rentalDao) {
        this.rentalDao = rentalDao;
    }

    // 대출 등록
    public void addRental(String rentalNo, String bookNo, String memberNo) {
        Member member = em.find(Member.class, memberNo);
        Rental rental = new Rental(rentalNo, bookNo, member,
                LocalDate.now(), null, false);
        rentalDao.save(rental);
    }

    // 회원별 대출 목록 조회
    public List<RentalDto> getMyRentals(String memberNo) {
        return rentalDao.findByMemberNo(memberNo).stream()
                .map(rental -> new RentalDto(
                        rental.getRental_no(),
                        rental.getBook_no(),
                        rental.getMember().getMemberNo(),
                        rental.getRental_date(),
                        rental.getReturn_date(),
                        rental.isExtension()
                ))
                .collect(Collectors.toList());
    }

    // 반납 처리
    public void returnRental(String rentalNo) {
        rentalDao.findById(rentalNo).ifPresent(rental -> {
            rental.setReturn_date(LocalDate.now());
        });
    }

    // 대출 취소(삭제)
    public void removeRental(String rentalNo){
        rentalDao.deleteByRentalNo(rentalNo);
    }
}