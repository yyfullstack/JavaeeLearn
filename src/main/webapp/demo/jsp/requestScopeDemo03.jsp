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
    ////取得requestScopedemo01.jsp设置的属性
    //取得设置的属性
    String refName = (String) request.getAttribute("name");
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
    Date refDate = (Date) request.getAttribute("date");
%>
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>

<%--以上的结果依然可以访问，但是如果，此时使用了超链接的方式传递的话，则属性是无法向下继续传递的。--%>
<%--使用超链接的形式跳转，这是客户端跳转，URL地址会改变--%>
<a href="${pageContext.request.contextPath}/demo/jsp/requestScopeDemo04.jsp">跳转到requestScopeDemo04.jsp</a>
</body>
</html>