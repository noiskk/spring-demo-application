package com.example.demo.controller;

import com.example.demo.dto.RentalDto;
import com.example.demo.service.RentalService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    // 내 대출 목록
    @GetMapping("/rentals")
    public String myRentals(HttpSession session, Model model) {
        String loginUser = (String) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        List<RentalDto> myRentals = rentalService.getMyRentals(loginUser);
        model.addAttribute("rentals", myRentals);
        return "rentals";
    }

    // 반납 처리
    @PostMapping("/rental/{rentalNo}/return")
    public String returnRental(@PathVariable String rentalNo, HttpSession session){
        String loginUser = (String) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        rentalService.returnRental(rentalNo);
        return "redirect:/rentals";
    }
}