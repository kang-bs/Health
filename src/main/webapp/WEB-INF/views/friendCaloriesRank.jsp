<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>친구 운동량 순위</title>
</head>
<body>
<h2>친구별 총 칼로리 소모량 순위</h2>
<table border="1">
    <tr>
        <th>순위</th>
        <th>이름</th>
        <th>이메일</th>
        <th>총 칼로리 소모량</th>
    </tr>
    <%
        List<Map<String, Object>> rankList = (List<Map<String, Object>>) request.getAttribute("rankList");
        int rank = 1;
        if (rankList != null && !rankList.isEmpty()) {
            for (Map<String, Object> row : rankList) {
    %>
    <tr>
        <td><%= rank++ %></td>
        <td><%= row.get("memberName") %></td>
        <td><%= row.get("memberEmail") %></td>
        <td><%= row.get("totalCalories") %> kcal</td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="4">친구의 운동 기록이 없습니다.</td></tr>
    <%
        }
    %>
</table>
<button type="button" onclick="location.href='/member/friend/list'">친구 목록으로</button>
<button type="button" onclick="location.href='/member/main'">메인으로</button>
</body>
</html>