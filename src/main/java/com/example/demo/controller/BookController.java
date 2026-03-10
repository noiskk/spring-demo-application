package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalService; // 추가
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final RentalService rentalService; // 추가된 서비스

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
    public String detail(@PathVariable String id, Model model) {
        Book book = bookService.getBookDetail(id);
        model.addAttribute("book", book);
        return "book-detail";
    }

    /**
     * 3. 도서 예약 처리 (임시)
     */
    @PostMapping("book/{id}/reserve")
    public String reserve(@PathVariable String id, HttpSession session) {
        String loginUser = (String) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/login";
        }

        // 도서 상태를 '예약됨'으로 변경
        // bookService.reserveBook(id); // TODO: Book PK가 String으로 바뀌면 수정

        // 대출 내역 생성 (임시 rentalNo 생성)
        String rentalNo = "R" + System.currentTimeMillis();
        rentalService.addRental(rentalNo, id, loginUser);

        return "redirect:/book/" + id;
    }

    /**
     * 4. 도서 예약 취소 처리 (임시)
     */
    @PostMapping("book/{id}/cancel")
    public String cancel(@PathVariable String id, @RequestParam String rentalNo, HttpSession session) {
        String loginUser = (String) session.getAttribute("loginUser");

        if (loginUser != null) {
            // bookService.cancelReservation(id); // TODO: Book PK가 String으로 바뀌면 수정
            rentalService.removeRental(rentalNo);
        }
        return "redirect:/book/" + id;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotFound(NoSuchElementException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error/404";
    }
}