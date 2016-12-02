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
    <title>session属性范围</title>
</head>
<body>
<%
    ////取得sessionScopedemo01.jsp设置的属性
    //取得设置的属性
    String refName = (String) session.getAttribute("name");
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
    Date refDate = (Date) session.getAttribute("date");
%>
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>

<%--使用超链接这种客户端跳转--%>
<a href="${pageContext.request.contextPath}/demo/jsp/sessionScopeDemo03.jsp">跳转到sessionScopeDemo03.jsp</a>
</body>
</html>