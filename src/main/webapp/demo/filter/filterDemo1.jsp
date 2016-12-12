<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>使用字符过滤器解决解决get、post请求方式下的中文乱码问题</title>
</head>
<body>
<h4>使用字符过滤器解决解决get、post请求方式下的中文乱码问题</h4>

<c:url value="/filter/servletDemo1" scope="page" var="servletDemo1">
    <c:param name="username" value="孤傲苍狼"/>
</c:url>

<a href="${servletDemo1}">超链接（get方式请求）</a>

<form action="/filter/servletDemo1" method="post">
    用户名：<input type="text" name="username" value="孤傲苍狼"/>
    <input type="submit" value="post方式提交">
</form>

</body>
</html>
