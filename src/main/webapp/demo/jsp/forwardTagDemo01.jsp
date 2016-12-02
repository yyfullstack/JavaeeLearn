<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<html>
<head>
    <title>使用jsp:forward标签跳转到</title>
</head>
<body>
<jsp:forward page="forwardTagDemo02.jsp">
    <jsp:param name="ref1" value="hello"/>
    <jsp:param name="ref2" value="gacl"/>
</jsp:forward>
</body>
</html>
