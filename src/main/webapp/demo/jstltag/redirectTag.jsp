<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: -- redirect标签实例</title>
</head>
<body>
<h4><c:out value="redirect标签实例"/></h4>
<c:redirect url="http://www.baidu.com">
    <%--在重定向时使用<c:param>标签为URL添加了两个参数：uname=GACL和password=123 --%>
    <c:param name="uname">GACL</c:param>
    <c:param name="password">123</c:param>
</c:redirect>
</body>
</html>
