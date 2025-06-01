# Health care program

#간단한 건강 관리 프로그램입니다.

## 🛠 개발 환경

- **IDE**: IntelliJ IDEA Community Edition
- **JDK**: Amazon Corretto OpenJDK 11
- **Database**: MySQL 8
- **ORM**: MyBatis
- **View**: JSP
- **Server**: Apache Tomcat 9

## 데이터베이스 및 계정 생성

```sql
-- 계정 생성하기
create database db_codingrecipe;
create user user_codingrecipe@localhost identified by '8605';
grant all privileges on db_codingrecipe.* to user_codingrecipe@localhost;

# MySQL 코드

DROP TABLE IF EXISTS friend;
DROP TABLE IF EXISTS exercise_record;
DROP TABLE IF EXISTS profile_table;
DROP TABLE IF EXISTS member_table;

CREATE TABLE member_table (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    memberEmail VARCHAR(20) UNIQUE,
    memberPassword VARCHAR(20),
    memberName VARCHAR(20),
    memberAge INT,
    memberMobile VARCHAR(30)
);

CREATE TABLE profile_table (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    weight DOUBLE,
    height DOUBLE,
    hobby VARCHAR(100),
    introduction VARCHAR(500),
    favorite_exercises VARCHAR(200),
    FOREIGN KEY (member_id) REFERENCES member_table(id) ON DELETE CASCADE
);

CREATE TABLE exercise_record (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    member_id BIGINT NOT NULL,
    exercise_date DATE NOT NULL,
    exercise_type VARCHAR(30) NOT NULL,
    duration INT NOT NULL,        -- 운동 시간(분)
    calories DOUBLE,              -- 자동 계산된 칼로리
    FOREIGN KEY (member_id) REFERENCES member_table(id) ON DELETE CASCADE
);

CREATE TABLE friend (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    requester_id BIGINT NOT NULL,   -- 친구 요청 보낸 회원
    addressee_id BIGINT NOT NULL,   -- 친구 요청 받은 회원
    status VARCHAR(10) NOT NULL,    -- 'REQUEST', 'ACCEPT', 'REJECT'
    requested_at DATETIME DEFAULT NOW(),
    FOREIGN KEY (requester_id) REFERENCES member_table(id) ON DELETE CASCADE,
    FOREIGN KEY (addressee_id) REFERENCES member_table(id) ON DELETE CASCADE
);

SELECT * FROM member_table;
SELECT * FROM profile_table;
SELECT * FROM exercise_record;
SELECT * FROM friend;
