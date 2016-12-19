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
    <title>修改客户信息</title>
</head>
<body>
<form action="${BASE}/customer_edit" method="post">
    <div>
        <lable>编号</lable>
        <input type="text" id="id" name="id" value="${customer.id}" readonly="readonly" >
    </div>
    <div>
        <lable>客户名称</lable>
        <input type="text" id="name" name="name" value="${customer.name}" >
    </div>
    <div>
        <lable>联系人</lable>
        <input type="text" id="contact"  name="contact" value="${customer.contact}">
    </div>

    <div>
        <lable>电话号码</lable>
        <input type="tel" id="telephone" name="telephone" value="${customer.telephone}" >
    </div>
    <div>
        <lable>邮箱</lable>
        <input type="email" id="email" name="email" value="${customer.email}" >
    </div>
    <input type="submit" value="提交">
</form>
</body>
</html>
