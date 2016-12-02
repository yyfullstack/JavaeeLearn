<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<html>
<head>
    <title>jsp的include指令测试</title>
</head>
<body>
<%@include file="jsp_fragments/head.jspf" %>
<h1>页面主体内容</h1>
<%@include file="jsp_fragments/foot.jspf"%>

</body>
</html>
