package com.example.demo.controller;

import com.example.demo.model.Loan;
import com.example.demo.service.LoanService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/loans")
    public String myLoans(HttpSession session, Model model) {
        String loginUser = (String) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        List<Loan> myLoans = loanService.getMyLoans(loginUser);
        model.addAttribute("loans", myLoans);
        return "loans";
    }
}