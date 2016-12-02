<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        div {
            min-height: 30px;
        }

        label {
            width: 100px;
            display: inline-block;
        }

        textarea {
            height: 200px;
            width: 400px;
            overflow: auto;
            word-break: break-all;
        }
    </style>
</head>
<body>
<h2>表单</h2>

<fieldset>
    <legend>form表单</legend>
    <form action="/javabean/loginServlet" method="post">
        <div>
            <label for="username">
                用户名
            </label>
            <input type="text" name="username" id="username" value="${user.username}">
        </div>
        <div>
            <label for="password">
                密码
            </label>
            <input type="password" name="password" id="password">
        </div>
        <div style="padding:10px 100px;">
            <input type="hidden" name="submitFlag" value="toLogin">
            <input type="submit" value="login">
        </div>
    </form>
</fieldset>
</body>
</html>
