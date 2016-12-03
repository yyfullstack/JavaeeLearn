<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: --表达式控制标签“catch”标签实例</title>
</head>
<body>
<h4>catch标签实例</h4>
<hr>
<%--把容易产生异常的代码放在<c:catch></c:catch>中，
   自定义一个变量errorInfo用于存储异常信息 --%>
<c:catch var="errorInfo">
    <%--实现了一段异常代码，向一个不存在的JavaBean中插入一个值--%>
    <c:set target="person" property="hao"></c:set>
</c:catch>

<%--用EL表达式得到errorInfo的值，并使用<c:out>标签输出 --%>
异常：<c:out value="${errorInfo}"/><br/>
异常 errorInfo.getMessage：<c:out value="${errorInfo.message}"/><br/>
异常 errorInfo.getCause：<c:out value="${errorInfo.cause}"/><br/>
异常 errorInfo.getStackTrace：<c:out value="${errorInfo.stackTrace}"/>

</body>
</html>
