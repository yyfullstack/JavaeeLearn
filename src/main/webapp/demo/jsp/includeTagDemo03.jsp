<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<html>
<head>
<title>使用jsp:param标签向被包含的页面传递参数</title>
</head>
<body>

<jsp:include page="includeTagDemo04.jsp">
    <jsp:param name="parm1" value="hello"/>
    <jsp:param name="parm2" value="gacl"/>
</jsp:include>
</body>
</html>
