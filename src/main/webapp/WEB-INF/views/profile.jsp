<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<h2>내 프로필</h2>
<p>몸무게: ${profile.weight} kg</p>
<p>키: ${profile.height} cm</p>
<p>취미: ${profile.hobby}</p>
<p>자기소개: ${profile.introduction}</p>
<p>관심 운동: ${profile.favoriteExercises}</p>


<!-- 내 프로필이 아닐 때만 친구 요청 버튼 노출 -->
<% Boolean isMe = (Boolean) request.getAttribute("isMe"); %>

<% Boolean isFriend = (Boolean) request.getAttribute("isFriend"); %>
<% if(isMe != null && !isMe && isFriend != null && !isFriend) { %>
<form action="/member/friend/request" method="post">
    <input type="hidden" name="toId" value="${member.id}">
    <button type="submit">친구 요청</button>
</form>
<% } %>

<% if(isMe != null && isMe) { %>
<button type="button" onclick="location.href='/member/friend/received'">받은 친구 요청</button>
<button type="button" onclick="location.href='/member/friend/list'">친구 목록 보기</button>
<% } %>


<button type="button" onclick="location.href='/member/main'">메인으로</button>

