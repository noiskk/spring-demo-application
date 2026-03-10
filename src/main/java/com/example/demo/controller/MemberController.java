package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    // 로그인 요청
    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        User user = memberService.login(username, password);

        if (user != null) {
            session.setAttribute("loginUser", user.getUsername());
            return "redirect:/";
        }

        model.addAttribute("error", "로그인 실패");
        return "login";
    }

    // 로그아웃 페이지
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // 메인 페이지
    @GetMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("loginUser") == null) {
            return "redirect:/login";
        }
        return "index";
    }

}
