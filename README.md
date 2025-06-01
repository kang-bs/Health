# Health care program

#간단한 건강 관리 프로그램입니다.

## 🛠 개발 환경

- **IDE**: IntelliJ IDEA Community Edition
- **JDK**: Amazon Corretto OpenJDK 11
- **Database**: MySQL 8
- **ORM**: MyBatis
- **View**: JSP
- **Server**: Apache Tomcat 9

🧩 Spring Framework 기반 웹 프로젝트 설정 가이드

이 프로젝트는 **Spring Framework**를 기반으로 제작되었으며, IntelliJ IDEA와 Maven, MySQL, Smart Tomcat 등을 이용한 개발 환경 설정 방법을 안내합니다.

---

## 📦 IntelliJ에서 Spring Maven 프로젝트 생성

1. **IntelliJ → 새 프로젝트 생성**
   - `Maven` 선택
   - Archetype에서 `maven-archetype-webapp` 선택 후 프로젝트 생성

2. **pom.xml 구성**
   - 생성 후 기존 `pom.xml` 파일을 본인의 설정으로 교체
   - IntelliJ 오른쪽 상단의 `M` 아이콘 클릭 →  
     `Load/Reload All Maven Projects`로 동기화 진행

3. **기본 파일 정리**
   - 자동 생성된 `index.jsp` 파일은 삭제 가능

4. **Java 디렉토리 생성**
   - `main` 폴더에서 우클릭 → `New → Directory`로 `java` 생성
   - 디렉토리 아이콘이 파란색이면 정상적으로 인식된 것

5. **패키지 생성 시 주의사항**
   - `java` 폴더 우클릭 → `New → Package`
   - `servlet-context.xml`에 지정된 네이밍 규칙과 동일한 패키지명으로 생성
   - `File → Settings → Appearance → Tree Appearance` 에서  
     `Compact Middle Packages` 옵션 체크 해제 추천 (패키지 계층 구조가 더 잘 보임)

6. **프로젝트 구조 구성**
   - `src/main` 구조는 GitHub에 업로드된 구조와 동일하게 구성할 것

---

## 🚀 Smart Tomcat 설정 및 실행

1. **Smart Tomcat 플러그인 설치**
   - IntelliJ 상단 톱니바퀴(⚙️) → `Plugins` → `Marketplace`
   - `Smart Tomcat` 검색 후 설치
   - 설치 후 IntelliJ 재시작 권장

2. **Run 설정**
   - 상단 실행 버튼 옆 `Add Configuration` 클릭
   - `+ → Smart Tomcat` 선택
   - 설정 예시:
     - **Name**: `Tomcat`
     - **Tomcat Server**: 본인의 컴퓨터에 설치된 Tomcat 경로 지정  
       예: `Apache Tomcat/9.0.104`
     - **Context Path**: `/`
     - **Port**: 기본 8080 사용, 충돌 시 변경 가능
     - **VM Options**:
       ```
       -Dfile.encoding=UTF-8
       ```

3. **Tomcat 설치 팁**
   - 마켓플레이스 설치 후 목록에 안 보일 경우 →  
     [Tomcat 공식 홈페이지](https://tomcat.apache.org)에서 직접 다운로드 후 경로 지정

---

## ☕ JDK 설정

- `File → Project Structure → Project SDK`에서 JDK **23**으로 설정
- C:\Users\rkdqh\.jdks\corretto-23.0.2 이렇게 이건 내 실행 경로 이므로 자신의 컴퓨터에 23을 다운받고 설정해주면 됨.

## 데이터 베이스 설정 방법
- 루트계정에서 밑의 계정생성하기 코드를 각각 실행해주면 됨.
- 그리고 new Connection을 만들고
- Connection Name: codingrecipe 로 입력
- UserName는 user_codingrecipe로 입력
- Default Schema: db_codingrecipe로 입력
- TestConnection은 설정한 비밀번호를 입력해주면 됨.
- 이때 설정한 비밀번호가 root-context.xml에 설정한 것과 일치하는지도 잘 확인해야함. 

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
