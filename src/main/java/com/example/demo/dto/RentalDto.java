package com.example.demo.dto;

import java.time.LocalDate;

public class RentalDto {
    private String rental_no;
    private String book_no;
    private String member_no;
    private LocalDate rental_date;
    private LocalDate return_date;
    private boolean extension;

    public RentalDto(String rental_no, String book_no, String member_no, LocalDate rental_date, LocalDate return_date, boolean extension) {
        this.rental_no = rental_no;
        this.book_no = book_no;
        this.member_no = member_no;
        this.rental_date = rental_date;
        this.return_date = return_date;
        this.extension = extension;
    }

    public String getRental_no() { return rental_no; }
    public String getBook_no() { return book_no; }
    public String getMember_no() { return member_no; }
    public LocalDate getRental_date() { return rental_date; }
    public LocalDate getReturn_date() { return return_date; }
    public boolean isExtension() { return extension; }
}
