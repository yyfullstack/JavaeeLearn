<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<html>
<head>
<title>接收从includeTagDemo03.jsp页面中传递过来的参数</title>
</head>
<body>
<h1>接收从includeTagDemo03.jsp页面中传递过来的参数：</h1>
<h2><%=request.getParameter("parm1")%></h2>
<h2><%=request.getParameter("parm2")%></h2>
</body>
</html>
