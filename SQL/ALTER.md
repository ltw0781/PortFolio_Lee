-- ALTER 문법

-- ALTER TABLE 기본 문법
-- ALTER TABLE 테이블명 작업내용;

-- 맨 마지막에 컬럼 추가
-- ALTER TABLE 테이블명 ADD COLUMN 컬럼명 데이터타입;

-- 특정 컬럼 뒤에 추가 (AFTER 사용)
-- ALTER TABLE 테이블명 ADD COLUMN 컬럼명 데이터타입 AFTER 기존컬럼명;

-- 가장 첫 번째에 추가 (FIRST)
-- ALTER TABLE user ADD COLUMN id INT FIRST;

-- 컬럼 삭제 (DROP COLUMN)
-- ALTER TABLE 테이블명 DROP COLUMN 컬럼명;

-- 컬럼 이름 변경 + 타입 변경 (CHANGE)
-- ALTER TABLE 테이블명 CHANGE COLUMN 기존컬럼명 새컬럼명 새데이터타입;

-- 컬럼 타입만 변경 (MODIFY)
-- ALTER TABLE user MODIFY COLUMN age BIGINT;

-- 컬럼 위치 변경 (CHANGE + AFTER 사용)
-- ALTER TABLE user CHANGE COLUMN age age INT AFTER name;

-- 테이블 이름 변경 (RENAME)
-- ALTER TABLE old_table RENAME new_table;

-- PRIMARY KEY 추가/삭제
-- ALTER TABLE user ADD PRIMARY KEY (user_id);

-- 기존 PK 삭제
-- ALTER TABLE user DROP PRIMARY KEY;

-- UNIQUE 제약조건 추가/삭제
-- ALTER TABLE user ADD CONSTRAINT uq_user_email UNIQUE (email);

-- FOREIGN KEY 추가/삭제
-- ALTER TABLE orders ADD CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES user(user_id);

-- 외래키 삭제
-- ALTER TABLE orders DROP FOREIGN KEY fk_orders_user;

-- DEFAULT 값 추가 및 변경
-- ALTER TABLE user ALTER COLUMN status SET DEFAULT 'ACTIVE';

-- DEFAULT 삭제
-- ALTER TABLE user ALTER COLUMN status DROP DEFAULT;

-- AUTO_INCREMENT 설정
-- ALTER TABLE user MODIFY COLUMN id BIGINT AUTO_INCREMENT;

-- 테이블 전체 문자셋 변경
-- ALTER TABLE user CONVERT TO CHARACTER SET utf8mb4;