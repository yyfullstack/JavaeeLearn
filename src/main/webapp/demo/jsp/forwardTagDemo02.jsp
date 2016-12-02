<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<html>
<head>
    <title>接收从forwardTagdemo01.jsp传递过来的参数</title>
</head>
<body>
<h1>跳转之后的页面！！</h1>
<h1>接收从forwardTagdemo01.jsp传递过来的参数：</h1>
<h1>ref1：<%=request.getParameter("ref1")%></h1>
<h1>ref2：<%=request.getParameter("ref2")%></h1>
</body>
</html>
