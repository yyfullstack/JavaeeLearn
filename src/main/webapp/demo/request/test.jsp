<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>Request 实现请求转发</title>

</head>
<body>
<h2>request对象</h2>
<span>使用普通方式获取存储数据</span>
<h3><%=(String)request.getAttribute("data")%></h3>
<span>使用EL表达式方式获取存储数据</span>
<h3>${requestScope.data}</h3>
<h3 style="color: #f00;">${data}</h3>
</body>
</html>
