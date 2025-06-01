<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.MemberDTO" %>
<html>
<head><title>회원 목록</title></head>
<body>
<h2>회원 목록</h2>
<table border="1">
    <tr>
        <th>이름</th>
        <th>이메일</th>
        <th>프로필 보기</th>
    </tr>
    <%
        List<MemberDTO> members = (List<MemberDTO>) request.getAttribute("members");
        if (members != null && !members.isEmpty()) {
            for (MemberDTO member : members) {
    %>
    <tr>
        <td><%= member.getMemberName() %></td>
        <td><%= member.getMemberEmail() %></td>
        <td>
            <a href="/member/profile?memberId=<%= member.getId() %>">프로필 보기</a>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="3">회원이 없습니다.</td></tr>
    <%
        }
    %>
</table>
<button type="button" onclick="location.href='/member/main'">메인으로</button>
</body>
</html>