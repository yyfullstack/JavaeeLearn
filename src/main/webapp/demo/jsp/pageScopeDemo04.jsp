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
    <title>pageContext属性范围的进一步补充</title>
</head>
<body>
<%
    pageContext.setAttribute("name","孤傲苍狼",PageContext.REQUEST_SCOPE);  //设置属性，并指明属性范围
    pageContext.setAttribute("date",new Date(),PageContext.REQUEST_SCOPE); //设置属性，并指明属性范围
%>
<%--使用jsp:forward标签进行服务器端跳转--%>
<jsp:forward page="/demo/jsp/pageScopeDemo05.jsp"/>
</body>
</html>