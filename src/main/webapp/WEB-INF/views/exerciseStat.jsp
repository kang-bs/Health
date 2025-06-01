<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.ExerciseStatDTO" %>

<h2>
    운동 기록 통계
    <a href="?type=date">[날짜별]</a>
    <a href="?type=week">[주별]</a>
    <a href="?type=month">[월별]</a>
</h2>
<table border="1">
    <tr>
        <th>기간</th>
        <th>총 운동시간(분)</th>
        <th>총 칼로리</th>
    </tr>
    <%
        List<ExerciseStatDTO> stats = (List<ExerciseStatDTO>) request.getAttribute("stats");
        if (stats != null && !stats.isEmpty()) {
            for (ExerciseStatDTO stat : stats) {
    %>
    <tr>
        <td><%= stat.getPeriod() %></td>
        <td><%= stat.getTotalDuration() %></td>
        <td><%= stat.getTotalCalories() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="3">통계 데이터가 없습니다.</td>
    </tr>
    <%
        }
    %>
</table>
<button type="button" onclick="location.href='/member/main'">메인으로</button>