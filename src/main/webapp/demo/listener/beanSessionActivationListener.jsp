<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/11/22 0022
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yckjsoft.javaee.listener.domain.JavaBeanDemo2" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>HttpSessionActivationListener监听器测试</title>
    <!--
        访问这个jsp页面，服务器就会马上创建一个HttpSession对象，然后将实现了HttpSessionActivationListener接口的JavaBean对象绑定到session对象中，
        这个jsp页面在等待1分钟之后没有人再次访问，那么服务器就会自动将这个HttpSession对象钝化(序列化)到硬盘上
    -->
</head>
<body>
一访问JSP页面，HttpSession就创建了，创建好的Session的Id是：${pageContext.session.id}
<%
    session.setAttribute("bean", new JavaBeanDemo2("白眼狼"));
%>
</body>
</html>
