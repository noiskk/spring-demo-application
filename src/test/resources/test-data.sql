-- =============================================
-- DDL - 테이블 생성 (의존성 순서대로)
-- =============================================

DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS rental;
DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS book_category;
DROP TABLE IF EXISTS member;

CREATE TABLE member (
    member_no       VARCHAR(20)     NOT NULL PRIMARY KEY,
    member_name     VARCHAR(50)     NOT NULL,
    resident_no     VARCHAR(20)     NOT NULL,
    address         VARCHAR(200),
    phone           VARCHAR(20),
    email           VARCHAR(100),
    overdue_fee     INT             DEFAULT 0
);

CREATE TABLE book_category (
    category_code   VARCHAR(20)     NOT NULL PRIMARY KEY,
    category_name   VARCHAR(100)    NOT NULL
);

CREATE TABLE book (
    book_no             VARCHAR(20)     NOT NULL PRIMARY KEY,
    title               VARCHAR(200)    NOT NULL,
    author              VARCHAR(100),
    publisher           VARCHAR(100),
    publish_date        DATE,
    isbn                VARCHAR(20),
    appendix_status     BOOLEAN         DEFAULT FALSE,
    category_code       VARCHAR(20),
    reservation_status  BOOLEAN         DEFAULT FALSE,
    FOREIGN KEY (category_code) REFERENCES book_category(category_code)
);

CREATE TABLE rental (
    rental_no       VARCHAR(20)     NOT NULL PRIMARY KEY,
    book_no         VARCHAR(20)     NOT NULL,
    member_no       VARCHAR(20)     NOT NULL,
    rental_date     DATE            NOT NULL,
    return_date     DATE,
    extension       BOOLEAN         DEFAULT FALSE,
    FOREIGN KEY (book_no)    REFERENCES book(book_no),
    FOREIGN KEY (member_no)  REFERENCES member(member_no)
);

CREATE TABLE reservation (
    reservation_seq INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    rental_no       VARCHAR(20)     NOT NULL,
    member_no       VARCHAR(20)     NOT NULL,
    FOREIGN KEY (rental_no)  REFERENCES rental(rental_no),
    FOREIGN KEY (member_no)  REFERENCES member(member_no)
);

-- =============================================
-- DML - 테스트 데이터 삽입
-- =============================================

-- 회원
INSERT INTO member VALUES ('M001', '조유진', '900101-1234567', '서울시 강남구', '010-1111-1111', 'kim@test.com', 0);
INSERT INTO member VALUES ('M002', '김시온', '920305-2345678', '서울시 마포구', '010-2222-2222', 'lee@test.com', 500);
INSERT INTO member VALUES ('M003', '박주호', '881212-1357924', '경기도 수원시', '010-3333-3333', 'park@test.com', 0);
INSERT INTO member VALUES ('M004', '남인서', '950820-2468013', '인천시 부평구', '010-4444-4444', 'choi@test.com', 1000);
INSERT INTO member VALUES ('M005', '유정호', '010101-3579246', '서울시 송파구', '010-5555-5555', 'jung@test.com', 0);

-- 도서 분류
INSERT INTO book_category VALUES ('001', '컴퓨터/IT');
INSERT INTO book_category VALUES ('002', '소설');
INSERT INTO book_category VALUES ('003', '자기계발');

-- 도서
INSERT INTO book VALUES ('B001', '스프링 부트 실전', '김영한', '한빛미디어', '2023-03-01', '978-89-6848-001-1', FALSE, '001', TRUE);
INSERT INTO book VALUES ('B002', '타임리프 완벽 가이드', '이영희', '위키북스', '2022-07-15', '978-89-6848-002-2', FALSE, '001', FALSE);
INSERT INTO book VALUES ('B003', '자바 마스터', '박지성', '인사이트', '2021-11-20', '978-89-6848-003-3', TRUE,  '001', FALSE);
INSERT INTO book VALUES ('B004', '객체지향의 사실과 오해', '조영호', '위키북스', '2015-06-17', '978-89-6848-004-4', FALSE, '001', FALSE);
INSERT INTO book VALUES ('B005', '채식주의자', '한강', '창비', '2007-10-30', '978-89-6848-005-5', FALSE, '002', FALSE);

-- 대여
-- B001은 M001이 대여 중 (예약 3건 연결 예정)
INSERT INTO rental VALUES ('R001', 'B001', 'M001', '2026-03-01', '2026-03-15', FALSE);
INSERT INTO rental VALUES ('R002', 'B002', 'M002', '2026-03-05', '2026-03-19', FALSE);
INSERT INTO rental VALUES ('R003', 'B003', 'M003', '2026-02-20', '2026-03-06', TRUE);

-- 예약 (B001 - R001에 3명이 예약)
INSERT INTO reservation (rental_no, member_no) VALUES ('R001', 'M002');
INSERT INTO reservation (rental_no, member_no) VALUES ('R001', 'M003');
INSERT INTO reservation (rental_no, member_no) VALUES ('R001', 'M004');
