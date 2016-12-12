<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>敏感词过滤器测试</title>
</head>
<body>
<form action="/filter/servletDemo3" method="post">
    姓名：<input  type="text" name="name"/><br/>
    留言内容：<textarea rows="10" cols="10" name="textarea"></textarea><br/>
    <input  type="submit"  value="提交"/>
</form>
</body>
</html>
