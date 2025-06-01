
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Healthcare Program</title>
    <style>
        body {
            background: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);
            min-height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .container {
            background: white;
            padding: 40px 30px 30px 30px;
            border-radius: 18px;
            box-shadow: 0 4px 16px rgba(0,0,0,0.08);
            text-align: center;
        }
        h2 {
            color: #2c3e50;
            margin-bottom: 32px;
            font-size: 2.2em;
            font-weight: bold;
            letter-spacing: 2px;
        }
        .btn {
            display: inline-block;
            background: #3498db;
            color: white;
            padding: 12px 32px;
            margin: 12px 10px 0 10px;
            border: none;
            border-radius: 6px;
            font-size: 1.1em;
            text-decoration: none;
            transition: background 0.3s;
            cursor: pointer;
            font-weight: 500;
        }
        .btn:hover {
            background: #217dbb;
        }
        .sub-link {
            display: block;
            margin-top: 18px;
            color: #888;
            text-decoration: none;
            font-size: 0.98em;
        }
        .sub-link:hover {
            color: #444;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Healthcare Program!!</h2>
    <a href="/member/save" class="btn">회원가입</a>
    <a href="/member/login" class="btn">로그인</a>
    <!--
    <a href="/member/memberList" class="sub-link">회원목록 조회</a>
    -->
</div>
</body>
</html>