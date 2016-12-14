<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/12/13 0013
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>注册页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/servlet/RegisterServlet"
      method="post">
    用户名：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    邮箱：<input type="text" name="email"><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
