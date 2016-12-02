<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<html>
<head>
    <title>jsp的jsp:include标签测试</title>
</head>
<body>
<jsp:include page="jsp_fragments/head.jspf"/>
<h1>页面主体内容</h1>
<jsp:include page="jsp_fragments/foot.jspf"/>

<%--可以看到，head.jspf和foot.jspf中的<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>没有解析执行，
而是原封不动地作为文本内容输出到页面上了，在IE下看不到<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>的输出，
在google和火狐浏览器下运行可以看到页面上输出<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>，
这说明jspf文件Tomcat服务器被当作纯文本文件处理了，没有当作jsp页面来解析执行，那么该如何解决这个问题
--%>
</body>
</html>
