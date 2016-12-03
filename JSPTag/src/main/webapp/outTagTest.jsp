<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>out标签测试</title>
</head>
<body>
<yc:out content="<a href='http://www.cnblogs.com'>访问博客园</a>" />

<yc:out content="<a href='http://www.cnblogs.com'>访问博客园</a>" escapeHtml="true"/>
</body>
</html>
