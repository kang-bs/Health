<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.ExerciseDTO" %>

<table>
    <tr>
        <th>날짜</th><th>운동종류</th><th>운동시간(분)</th><th>칼로리</th> <th>삭제</th>
    </tr>
    <%
        List<ExerciseDTO> records = (List<ExerciseDTO>) request.getAttribute("records");
        if (records != null) {
            for (ExerciseDTO rec : records) {
    %>
    <tr>
        <td><%= rec.getExerciseDate() %></td>
        <td><%= rec.getExerciseType() %></td>
        <td><%= rec.getDuration() %></td>
        <td><%= rec.getCalories() %></td>

        <td>
            <!-- 개별 기록 삭제 -->
            <form action="/member/exerciseDeleteById" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= rec.getId() %>">
                <button type="submit" onclick="return confirm('이 운동기록을 삭제할까요?');">삭제</button>
            </form>
        </td>

    </tr>
    <%
            }
        }
    %>
</table>
<p>총 소모 칼로리: <%= request.getAttribute("totalCalories") %> kcal</p>

<!-- 날짜별 전체 기록 삭제 폼 -->
<form action="/member/exerciseDeleteByDate" method="post" style="margin-top:20px;">
    <label>날짜별 전체 기록 삭제: </label>
    <input type="date" name="exerciseDate" required>
    <button type="submit" onclick="return confirm('이 날짜의 모든 운동기록을 삭제할까요?');">날짜별 삭제</button>
</form>
<button type="button" onclick="location.href='/member/main'">메인으로</button>