<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/12/19 0019
  Time: 14:04
  To change tdis template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看客户信息</title>
</head>
<body>
<div>
    <lable>客户名称</lable>
    <span id="name">${customer.name}</span>
</div>
<div>
    <lable>联系人</lable>
    <span id="contact">${customer.contact}</span>
</div>

<div>
    <lable>电话号码</lable>
    <span id="telephone">${customer.telephone}</span>
</div>
<div>
    <lable>邮箱</lable>
    <span id="email">${customer.email}</span>
</div>
</body>
</html>
