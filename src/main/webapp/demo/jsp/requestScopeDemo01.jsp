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
    <title>request属性范围</title>
</head>
<body>
<%

    request.setAttribute("name", "孤傲苍狼");  //设置属性
    request.setAttribute("date", new Date()); //设置属性
%>
<%--使用jsp:forward标签进行服务器端跳转--%>
<jsp:forward page="/demo/jsp/requestScopeDemo02.jsp"/>
</body>
</html>