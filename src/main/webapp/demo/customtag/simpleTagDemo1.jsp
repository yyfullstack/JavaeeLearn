<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>用简单标签控制标签体是否执行</title>
</head>
<body>
<%--在jsp页面中使用自定义标签 demo3标签--%>
<yc:simpleTagDemo1>
    <h3>jsp 页面内容</h3>
</yc:simpleTagDemo1>

</body>
</html>
