<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="/ELFunction" %>
<html>
<head>
    <title>使用EL调用Java方法</title>
</head>
<body>
<%--使用EL调用filter方法--%>
${fn:filter("<a href=''>点点</a>")}
</body>
</html>