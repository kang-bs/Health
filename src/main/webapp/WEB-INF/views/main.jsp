<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 20px;
            color: #333;
        }
        .container {
            max-width: 600px;
            margin: 40px auto;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.08);
        }
        h2 {
            color: #2c3e50;
            margin-bottom: 25px;
        }
        .button-group {
            display: flex;
            flex-wrap: wrap;
            margin-bottom: 15px;
        }
        .button-group button {
            background-color: #3498db;
            border: none;
            color: white;
            padding: 12px 24px;
            margin: 8px 10px 8px 0;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
            flex: 1 1 45%;
            margin-bottom: 10px;
        }
        .button-group button:hover {
            background-color: #2980b9;
        }
        #deleteForm {
            margin-top: 20px;
            padding: 15px;
            background-color: #ffe6e6;
            border: 1px solid #ff4d4d;
            border-radius: 5px;
            width: 300px;
        }
        input[type=password] {
            padding: 8px;
            width: 180px;
            margin-right: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>${sessionScope.loginEmail} 님 환영합니다.</h2>
    <div class="button-group">
        <button onclick="update()">내정보 수정하기</button>
        <button onclick="logout()">로그아웃</button>
    </div>
    <div class="button-group">
        <button onclick="location.href='/member/profile'">프로필 보기</button>
        <button onclick="location.href='/member/profileEdit'">프로필 수정</button>
        <button onclick="showDeleteForm()">계정 제거</button>
    </div>
    <div class="button-group">
        <button onclick="location.href='/member/exerciseAdd'">운동기록 등록</button>
        <button onclick="location.href='/member/exerciseList'">운동기록 조회</button>
        <button type="button" onclick="location.href='/member/exerciseStat'">운동기록 통계</button>
    </div>
    <div class="button-group">
        <button type="button" onclick="location.href='/member/memberList'">회원 목록 보기</button>
        <button type="button" onclick="location.href='/member/recommend'">추천 친구 보기</button>
    </div>
    <div id="deleteForm" style="display:none;">
        <form action="/member/deleteAccount" method="post">
            비밀번호: <input type="password" name="memberPassword" required />
            <button type="submit">계정 삭제</button>
        </form>
    </div>
</div>
<script>
    function showDeleteForm() {
        document.getElementById('deleteForm').style.display = 'block';
    }
    const update = () => {
        location.href = "/member/update";
    }
    const logout = () => {
        location.href = "/member/logout";
    }
</script>
</body>
</html>