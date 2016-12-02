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
<%--<%
    //request属性范围表示在一次服务器跳转中有效，只要是服务器跳转，则设置的request属性可以一直传递下去
    //取得设置的属性
    String refName = (String) request.getAttribute("name");
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
    Date refDate = (Date) request.getAttribute("date");
%>
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>--%>
<jsp:forward page="/demo/jsp/requestScopeDemo03.jsp"/>
</body>
</html>