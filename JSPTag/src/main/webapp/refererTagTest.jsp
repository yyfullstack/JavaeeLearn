<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>

<%--在Jsp页面中使用防盗链标签
 当用户尝试直接通过URL地址(http://localhost:8080/jspTag/refererTagTest.jsp)访问这个页面时，
 防盗链标签的标签处理器内部就会进行处理，将请求重定向到/index.jsp
--%>
<yc:referer site="http://localhost:8080" page="/index.jsp"/>
<html>
<head>
    <title>防盗链标签测试</title>
</head>
<body>
网站内部资料
</body>
</html>
