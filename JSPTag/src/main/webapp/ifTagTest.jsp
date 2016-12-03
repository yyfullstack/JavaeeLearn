<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>


<html>
<head>
    <title>if链标签测试</title>
</head>
<body>
<yc:if test="true">
    <h3>网站内部资料</h3>
</yc:if>
<yc:if test="false">
    <h3>机密资料，无法查看</h3>
</yc:if>
</body>
</html>
