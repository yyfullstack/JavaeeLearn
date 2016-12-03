<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>用简单标签控制标签余下的Jsp不执行</title>
</head>
<body>
<h1>孤傲苍狼</h1>
<%--在jsp页面中使用自定义标签 --%>
<yc:simpleTagDemo4/>
<!-- 这里的内容位于 标签后面，因此不会输出到页面上 -->
<h1>白虎神皇</h1>

</body>
</html>
