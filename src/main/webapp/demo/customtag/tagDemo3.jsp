<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>控制页面内容重复执行5次</title>
</head>
<body>
<%--在jsp页面中使用自定义标签 demo3标签--%>
<yc:tagDemo3>
    <h3>jsp 页面内容</h3>
</yc:tagDemo3>
</body>
</html>
