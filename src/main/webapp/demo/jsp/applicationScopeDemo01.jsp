<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/10/26 0026
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>application属性范围</title>
</head>
<body>
<%

    application.setAttribute("name", "孤傲苍狼");  //设置属性
    application.setAttribute("date", new Date()); //设置属性
%>
<%--使用超链接这种客户端跳转--%>
<a href="${pageContext.request.contextPath}/demo/jsp/applicationScopeDemo02.jsp">跳转到applicationScopeDemo02.jsp</a>
</body>
</html>