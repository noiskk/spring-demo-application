package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor   // Lombok: 생성자 주입 자동 생성
public class BookController {

    private final BookService bookService;

    /**
     * 1. 도서 목록 페이지
     */
    @GetMapping
    public String list(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    /**
     * 2. 도서 상세 페이지
     */
    @GetMapping("/book/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Book book = bookService.getBookDetail(id);
        model.addAttribute("book", book);
        return "book-detail";
    }

    /**
     * 3. 도서 예약 처리
     */
    @PostMapping("book/{id}/reserve")
    public String reserve(@PathVariable Long id, HttpSession session) {
        String loginUser = (String) session.getAttribute("loginUser");

        // 로그인 여부 확인
        if (loginUser == null) {
            return "redirect:/login";
        }

        bookService.reserveBook(id);
        return "redirect:/book/" + id;
    }

    /**
     * 4. 도서 예약 취소 처리
     */
    @PostMapping("book/{id}/cancel")
    public String cancel(@PathVariable Long id, HttpSession session) {
        String loginUser = (String) session.getAttribute("loginUser");

        if (loginUser != null) {
            bookService.cancelReservation(id);
        }
        return "redirect:/book/" + id;
    }

    // 에러 처리
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(NoSuchElementException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404"; // 에러 전용 HTML 페이지로 이동
    }
}