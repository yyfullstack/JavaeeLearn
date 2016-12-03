<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>foreach标签测试</title>
</head>
<%
    List<String> data = new ArrayList<String>();
    data.add("jack");
    data.add("lily");
    data.add("tom");
    pageContext.setAttribute("data", data);
%>
<body>
<yc:foreach items="${data}" var="str">
    ${str}<br/>
</yc:foreach>
</body>
</html>
