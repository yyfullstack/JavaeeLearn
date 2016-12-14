<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/12/13 0013
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>login2.jsp登录页面</title>
</head>

<body>
<fieldset>
    <legend>用户登录</legend>
    <form action="${pageContext.request.contextPath}/login/handle.do" method="post">
        用户名：<input type="text" value="${param.usename}" name="usename">
        <br/>
        密码：<input type="text" value="${param.pwd}" name="pwd">
        <br/>
        <input type="submit" value="登录"/>
    </form>
</fieldset>
<hr/>
<label style="color: red;">${msg}</label>
</body>
</html>
