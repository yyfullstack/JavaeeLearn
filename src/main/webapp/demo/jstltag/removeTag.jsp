<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: --表达式控制标签“remove”标签的使用</title>
</head>
<body>
<ul>
    <c:set var="name" scope="session">孤傲苍狼</c:set>
    <c:set var="age" scope="session">25</c:set>
    <li><c:out value="${sessionScope.name}"></c:out></li>
    <li><c:out value="${sessionScope.age}"></c:out></li>
    <%--使用remove标签移除age变量 --%>
    <c:remove var="age"/>
    <li><c:out value="${sessionScope.name}"></c:out></li>
    <li><c:out value="${sessionScope.age}"></c:out></li>
</ul>
</body>
</html>
