<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/11/22 0022
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yckjsoft.javaee.listener.domain.JavaBeanDemo1" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>HttpSessionBindingListener监听器测试</title>
</head>
<body>
<%
    //将javabean对象绑定到Session中
    session.setAttribute("bean", new JavaBeanDemo1("白眼狼"));
    //从Session中删除javabean对象
    session.removeAttribute("bean");
%>
</body>
</html>
