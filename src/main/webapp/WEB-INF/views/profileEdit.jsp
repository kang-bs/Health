<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form action="/member/profileEdit" method="post">
    <input type="hidden" name="memberId" value="${profile.memberId}" />
    몸무게: <input type="number" step="0.1" name="weight" value="${profile.weight}" /><br>
    키: <input type="number" step="0.1" name="height" value="${profile.height}" /><br>
    취미: <input type="text" name="hobby" value="${profile.hobby}" /><br>
    자기소개: <textarea name="introduction" rows="4" cols="40">${profile.introduction}</textarea><br>
    <!--<button type="submit">저장</button> -->
    관심 운동:<br>
    <input type="checkbox" name="favoriteExercises" value="볼링">볼링
    <input type="checkbox" name="favoriteExercises" value="줄넘기">줄넘기
    <input type="checkbox" name="favoriteExercises" value="태권도">태권도
    <input type="checkbox" name="favoriteExercises" value="유도">유도
    <input type="checkbox" name="favoriteExercises" value="당구">당구
    <input type="checkbox" name="favoriteExercises" value="검도">검도
    <input type="checkbox" name="favoriteExercises" value="등산">등산
    <input type="checkbox" name="favoriteExercises" value="요가">요가
    <input type="checkbox" name="favoriteExercises" value="수영">수영
    <input type="checkbox" name="favoriteExercises" value="스키">스키
    <input type="checkbox" name="favoriteExercises" value="골프">골프
    <input type="checkbox" name="favoriteExercises" value="탁구">탁구
    <input type="checkbox" name="favoriteExercises" value="테니스">테니스
    <input type="checkbox" name="favoriteExercises" value="배드민턴">배드민턴
    <input type="checkbox" name="favoriteExercises" value="야구">야구
    <input type="checkbox" name="favoriteExercises" value="배구">배구
    <input type="checkbox" name="favoriteExercises" value="농구">농구
    <input type="checkbox" name="favoriteExercises" value="축구">축구
    <input type="checkbox" name="favoriteExercises" value="자전거">자전거
    <input type="checkbox" name="favoriteExercises" value="런닝">런닝
    <input type="checkbox" name="favoriteExercises" value="헬스">헬스
    <button type="submit">저장</button>
</form>

