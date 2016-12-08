<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%--在WEB应用中使用国际化标签库实现固定文本的国际化--%>
    <title>国际化(i18n)测试</title>
</head>
<body>
<fmt:setBundle var="bundle"  basename="myproperties" scope="page"/>
<form action="" method="post">
    <fmt:message key="username" bundle="${bundle}"/>:<input type="text" name="username"><br/>
    <fmt:message key="password" bundle="${bundle}" />:<input type="password" name="password">
    <br>
    <input type="submit" value="<fmt:message key='submit' bundle='${bundle}'/> ">
</form>
</body>
</html>