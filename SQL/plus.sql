-- Active: 1763393025352@@127.0.0.1@3306@example
-- ============================================
-- 1) DROP TABLE: FK 의존 관계 고려하여 삭제
-- ============================================

DROP TABLE IF EXISTS `carts`;
DROP TABLE IF EXISTS `user_auth`;
DROP TABLE IF EXISTS `comments`;
DROP TABLE IF EXISTS `file`;
DROP TABLE IF EXISTS `payments`;
DROP TABLE IF EXISTS `board`;
DROP TABLE IF EXISTS `products`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `common`;


-- ============================================
-- 2) CREATE TABLE: 참조받는 테이블 먼저 생성
-- ============================================

-- COMMON
CREATE TABLE `common` (
    `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `id` VARCHAR(255) NOT NULL COMMENT 'UK',
    `created_at` TIMESTAMP NOT NULL COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL COMMENT '수정일자',
    PRIMARY KEY (`no`)
);

-- USERS
CREATE TABLE `users` (
    `username` VARCHAR(100) NOT NULL COMMENT '회원아이디',
    `password` VARCHAR(100) NOT NULL COMMENT '비밀번호',
    `name` VARCHAR(100) NOT NULL COMMENT '이름',
    `email` VARCHAR(200) NULL DEFAULT NULL COMMENT '이메일',
    `phone` VARCHAR(200) NULL DEFAULT NULL COMMENT '전화번호',
    `address` VARCHAR(200) NULL DEFAULT NULL COMMENT '주소',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
    `enabled` INT NOT NULL DEFAULT 1 COMMENT '권한상태',
    `status` INT NOT NULL DEFAULT 0 COMMENT '진행상태',
    PRIMARY KEY (`username`)
);

-- PRODUCTS
CREATE TABLE `products` (
    `product_no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '상품번호',
    `product_name` VARCHAR(255) NOT NULL COMMENT '상품명',
    `description` TEXT NULL COMMENT '상품설명',
    `price` DECIMAL(10,2) NOT NULL COMMENT '상품가격',
    `stock` INT NOT NULL DEFAULT 0 COMMENT '상품재고',
    `product_nm` VARCHAR(100) NULL COMMENT '상품종류',
    `product_img` VARCHAR(255) NULL COMMENT '상품이미지',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`product_no`)
);

-- BOARD
CREATE TABLE `board` (
    `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `id` VARCHAR(255) NOT NULL COMMENT 'UK',
    `category` VARCHAR(100) NOT NULL COMMENT '카테고리',
    `title` VARCHAR(255) NOT NULL COMMENT '제목',
    `writer` VARCHAR(100) NOT NULL COMMENT '작성자',
    `content` TEXT NULL COMMENT '내용',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
    `view_count` INT NOT NULL DEFAULT 0 COMMENT '조회수',
    PRIMARY KEY (`no`)
);

-- PAYMENTS (users 필요)
CREATE TABLE `payments` (
    `payment_no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '결제번호',
    `order_no` VARCHAR(100) NOT NULL COMMENT '주문번호',
    `total_amount` DECIMAL(10,2) NOT NULL COMMENT '총주문금액',
    `payment_method` VARCHAR(50) NOT NULL COMMENT '결제방식',
    `status` VARCHAR(50) NOT NULL COMMENT '상태',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `Field` VARCHAR(255) NULL,
    `username` VARCHAR(100) NOT NULL COMMENT '회원아이디',
    PRIMARY KEY (`payment_no`)
);

-- FILE TABLE (독립적)
CREATE TABLE `file` (
    `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `id` VARCHAR(255) NOT NULL COMMENT 'UK',
    `file_name` TEXT NOT NULL COMMENT '파일명',
    `file_path` TEXT NOT NULL COMMENT '파일경로',
    `file_size` BIGINT NULL COMMENT '용량',
    `type` ENUM('main','sub') NOT NULL COMMENT '타입',
    `parent_table` VARCHAR(100) NOT NULL COMMENT '부모테이블',
    `parent_no` BIGINT NOT NULL COMMENT '부모PK',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`no`)
);

-- COMMENTS (board 필요)
CREATE TABLE `comments` (
    `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'PK',
    `id` VARCHAR(255) NOT NULL COMMENT 'UK',
    `board_no` BIGINT NOT NULL COMMENT 'FK: board.no',
    `writer` VARCHAR(100) NOT NULL COMMENT '작성자',
    `content` TEXT NULL COMMENT '내용',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일자',
    PRIMARY KEY (`no`)
);

-- USER_AUTH (users 필요)
CREATE TABLE `user_auth` (
    `no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '번호',
    `username` VARCHAR(100) NOT NULL COMMENT '회원아이디',
    `auth` VARCHAR(255) NOT NULL COMMENT '권한',
    PRIMARY KEY (`no`, `username`)
);

-- CARTS (products 필요)
CREATE TABLE `carts` (
    `cart_no` BIGINT NOT NULL AUTO_INCREMENT COMMENT '장바구니번호',
    `product_no` BIGINT NOT NULL COMMENT '상품번호',
    `quantity` INT NOT NULL DEFAULT 0 COMMENT '수량',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일자',
    PRIMARY KEY (`cart_no`, `product_no`)
);


-- ============================================
-- 3) FOREIGN KEYS: 마지막에 일괄 추가
-- ============================================

ALTER TABLE `carts`
ADD CONSTRAINT `FK_products_TO_carts_1`
FOREIGN KEY (`product_no`)
REFERENCES `products` (`product_no`);

ALTER TABLE `user_auth`
ADD CONSTRAINT `FK_users_TO_user_auth_1`
FOREIGN KEY (`username`)
REFERENCES `users` (`username`);

-- (선택) comments.board_no ← board.no
-- 필요하면 활성화
-- ALTER TABLE `comments`
-- ADD CONSTRAINT `FK_board_TO_comments`
-- FOREIGN KEY (`board_no`)
-- REFERENCES `board` (`no`);

