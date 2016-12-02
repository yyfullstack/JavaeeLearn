<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>控制jsp页面是否执行</title>
</head>
<body>
<h1>jsp页面的内容1</h1>
<%--在jsp页面中使用自定义标签 demo2标签是不带标签体的--%>
<yc:tagDemo2/>
<h1>jsp页面的内容2</h1>
</body>
</html>
