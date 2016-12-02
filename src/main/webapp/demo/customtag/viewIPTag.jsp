<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>

<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>输出客户端的IP</title>
</head>
<body>
你的IP地址是(使用java代码获取输出)：
<%
    String ip = request.getRemoteAddr();
    out.write(ip);
%>
你的IP地址是(使用自定义标签获取输出)：
<%--使用自定义标签viewIP --%>
<yc:viewIP/>

</body>
</html>
