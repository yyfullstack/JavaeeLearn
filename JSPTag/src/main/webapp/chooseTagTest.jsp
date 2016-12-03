<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>


<html>
<head>
    <title>when和otherwise标签测试</title>
</head>
<body>
<yc:choose>
    <yc:when test="${user == null}">
        when标签标签体输出的内容：<h3>用户为空</h3>
    </yc:when>
    <yc:otherwise>
        用户不为空
    </yc:otherwise>
</yc:choose>

<yc:choose>
    <yc:when test="${user != null}">
        用户不为空
    </yc:when>
    <yc:otherwise>
        otherwise标签标签体输出的内容：<h3>用户为空</h3>
    </yc:otherwise>
</yc:choose>
</body>
</html>
