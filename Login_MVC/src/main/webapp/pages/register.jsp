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
    <title>用户注册</title>
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
    <form action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">

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
        <div>
            <label for="confirmPwd">
                确认密码
            </label>
            <input type="password" name="confirmPwd" id="confirmPwd" value="${formbean.confirmPwd}">
            ${formbean.errors.confirmPwd}
        </div>
        <div>
            <label for="email">
                邮箱
            </label>
            <input type="text" name="email" id="email" value="${formbean.email}">
            ${formbean.errors.email}
        </div>
        <div>
            <label for="birthday">
                生日
            </label>
            <input type="text" name="birthday" id="birthday" value="${formbean.birthday}">
            ${formbean.errors.birthday}
        </div>
        <div style="padding:10px 100px;">
            <input type="submit" value="注册">
            <input type="reset" value="清空">
        </div>
    </form>
</fieldset>
</body>
</html>
