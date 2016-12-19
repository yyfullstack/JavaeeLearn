<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/12/19 0019
  Time: 14:04
  To change tdis template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>新增客户信息</title>
</head>
<body>
<form action="${BASE}/customer_create" method="post">
    <div>
        <lable>客户名称</lable>
        <input type="text" id="name" name="name">
    </div>
    <div>
        <lable>联系人</lable>
        <input type="text" id="contact" name="contact">
    </div>

    <div>
        <lable>电话号码</lable>
        <input type="tel" id="telephone" name="telephone" >
    </div>
    <div>
        <lable>邮箱</lable>
        <input type="email" id="email" name="email">
    </div>
    <input type="submit" value="提交">
</form>
</body>
</html>
