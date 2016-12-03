<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%@ taglib prefix="yc" uri="/yctag" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>html转义标签测试</title>
</head>
<body>
<yc:htmlEscape>
    <a href="http://www.cnblogs.com">访问博客园</a>
</yc:htmlEscape>
</body>
</html>
