<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codingrecipe.member.dto.MemberDTO" %>
<html>
<head><title>친구 목록</title></head>
<body>
<h2>친구 목록</h2>
<table border="1">
    <tr>
        <th>이름</th>
        <th>이메일</th>
        <th>프로필</th>
        <th>삭제</th>
    </tr>
    <%
        List<MemberDTO> friends = (List<MemberDTO>) request.getAttribute("friends");
        if (friends != null && !friends.isEmpty()) {
            for (MemberDTO friend : friends) {
    %>
    <tr>
        <td><%= friend.getMemberName() %></td>
        <td><%= friend.getMemberEmail() %></td>
        <td>
            <a href="/member/profile?memberId=<%= friend.getId() %>">프로필 보기</a>
        </td>
        <td>
            <form action="/member/friend/delete" method="post" style="display:inline;">
                <input type="hidden" name="friendId" value="<%= friend.getId() %>">
                <button type="submit" onclick="return confirm('정말 친구를 삭제하시겠습니까?');">삭제</button>
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="3">친구가 없습니다.</td></tr>
    <%
        }
    %>
</table>

<button type="button" onclick="location.href='/member/friend/rank'">친구 칼로리 순위보기</button>

<a href="/member/main">메인으로</a>
</body>
</html>