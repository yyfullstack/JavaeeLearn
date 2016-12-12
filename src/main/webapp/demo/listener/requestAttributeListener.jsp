<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/11/22 0022
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>ServletRequestAttributeListener监听器测试</title>
</head>
<body>
<%
    request.setAttribute("name", "白眼狼");
    request.setAttribute("name", "狼图腾");
    request.removeAttribute("name");
%>
</body>
</html>
