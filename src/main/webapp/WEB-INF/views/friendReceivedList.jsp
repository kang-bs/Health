<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.MemberDTO" %>
<html>
<head><title>받은 친구 요청</title></head>
<body>
<h2>받은 친구 요청</h2>
<table border="1">
    <tr>
        <th>이름</th>
        <th>이메일</th>
        <th>수락</th>
        <th>거절</th>
    </tr>
    <%
        List<MemberDTO> receivedRequests = (List<MemberDTO>) request.getAttribute("receivedRequests");
        if (receivedRequests != null && !receivedRequests.isEmpty()) {
            for (MemberDTO user : receivedRequests) {
    %>
    <tr>
        <td><%= user.getMemberName() %></td>
        <td><%= user.getMemberEmail() %></td>
        <td>
            <form action="/member/friend/accept" method="post" style="display:inline;">
                <input type="hidden" name="fromId" value="<%= user.getId() %>">
                <button type="submit">수락</button>
            </form>
        </td>
        <td>
            <form action="/member/friend/reject" method="post" style="display:inline;">
                <input type="hidden" name="fromId" value="<%= user.getId() %>">
                <button type="submit">거절</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="4">받은 친구 요청이 없습니다.</td></tr>
    <%
        }
    %>
</table>
<a href="/member/main">메인으로</a>
</body>
</html>