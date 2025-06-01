<<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<form action="/member/exerciseAdd" method="post">
    날짜: <input type="date" name="exerciseDate" required><br>
    운동 종목:
    <select name="exerciseType" required>
        <option value="볼링">볼링</option>
        <option value="줄넘기">줄넘기</option>
        <option value="태권도">태권도</option>
        <option value="유도">유도</option>
        <option value="당구">당구</option>
        <option value="검도">검도</option>
        <option value="등산">등산</option>
        <option value="요가">요가</option>
        <option value="수영">수영</option>
        <option value="스키">스키</option>
        <option value="골프">골프</option>
        <option value="탁구">탁구</option>
        <option value="테니스">테니스</option>
        <option value="배드민턴">배드민턴</option>
        <option value="야구">야구</option>
        <option value="배구">배구</option>
        <option value="농구">농구</option>
        <option value="축구">축구</option>
        <option value="자전거">자전거</option>
        <option value="런닝">런닝</option>
        <option value="헬스">헬스</option>
    </select><br>
    운동 시간(분): <input type="number" name="duration" required><br>
    <button type="submit">운동 기록 저장</button>
    <button type="button" onclick="location.href='/member/exerciseList'">운동기록 조회</button>
</form>
