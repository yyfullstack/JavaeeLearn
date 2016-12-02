<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>控制标签体是否执行</title>
</head>
<body>
<%--在jsp页面中使用自定义标签 demo1标签是带有标签体的，标签体的内容是"孤傲苍狼"这几个字符串--%>
<yc:tagDemo1>
    孤傲苍狼
</yc:tagDemo1>
</body>
</html>
