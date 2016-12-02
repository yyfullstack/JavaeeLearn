<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/10/31 0031
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>用户登录</title>
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
<fieldset>
    <legend>form表单</legend>
    <form action="${pageContext.request.contextPath}/servlet/LoginServlet" method="post">

        <div>
            <label for="userName">
                用户名
            </label>
            <input type="text" name="userName" id="userName" value="${formbean.userName}">
            ${formbean.errors.userName}
        </div>
        <div>
            <label for="userPwd">
                密码
            </label>
            <input type="password" name="userPwd" id="userPwd" value="${formbean.userPwd}">
            ${formbean.errors.userPwd}
        </div>
        <div style="padding:10px 100px;">
            <input type="submit" value="登录">
            <input type="reset" value="清空">
        </div>
    </form>
</fieldset>
</body>
</html>
