package com.example.demo.service;

import com.example.demo.dao.BookDao;
import com.example.demo.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;

    // 예약 수행
    public void reserveBook(Long id) {
        bookDao.findById(id).ifPresent(book -> {
            if (!book.isReserved()) {
                book.setReserved(true);
                bookDao.update(book);
            }
        });
    }

    // 예약 취소
    public void cancelReservation(Long id) {
        bookDao.findById(id).ifPresent(book -> {
            book.setReserved(false);
            bookDao.update(book);
        });
    }

    public List<Book> getAllBooks() {
        return bookDao.findAll();
    }

    public Book getBookDetail(Long id) {
        return bookDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("도서를 찾을 수 없습니다."));
    }
}