<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.MemberDTO" %>
<html>
<head><title>추천 친구 목록</title></head>
<body>
<h2>추천 친구 목록</h2>
<table border="1">
    <tr>
        <th>이름</th>
        <th>이메일</th>
        <th>프로필</th>
    </tr>
    <%
        List<MemberDTO> recommendedFriends = (List<MemberDTO>) request.getAttribute("recommendedFriends");
        if (recommendedFriends != null && !recommendedFriends.isEmpty()) {
            for (MemberDTO user : recommendedFriends) {
    %>
    <tr>
        <td><%= user.getMemberName() %></td>
        <td><%= user.getMemberEmail() %></td>
        <td>
            <a href="/member/profile?memberId=<%= user.getId() %>">프로필 보기</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="3">추천 친구가 없습니다.</td></tr>
    <%
        }
    %>
</table>
<button type="button" onclick="location.href='/member/main'">메인으로</button>
</body>
</html>