<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.MemberDTO" %>
<html>
<head><title>보낸 친구 요청</title></head>
<body>
<h2>보낸 친구 요청</h2>
<table border="1">
    <tr>
        <th>이름</th>
        <th>이메일</th>
        <th>프로필</th>
    </tr>
    <%
        List<MemberDTO> sentRequests = (List<MemberDTO>) request.getAttribute("sentRequests");
        if (sentRequests != null && !sentRequests.isEmpty()) {
            for (MemberDTO user : sentRequests) {
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
    <tr><td colspan="3">보낸 친구 요청이 없습니다.</td></tr>
    <%
        }
    %>
</table>
<a href="/member/main">메인으로</a>
</body>
</html>