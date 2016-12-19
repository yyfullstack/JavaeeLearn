<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/12/19 0019
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>客户管理</title>
</head>
<body>
<a href="${BASE}/customer_create">新增</a>
<table>
    <thead>
    <tr>
        <th>客户名称</th>
        <th>联系人</th>
        <th>电话号码</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="customer" items="${customerList}">
        <tr>
            <td>
                <a href="${BASE}/customer_show?id=${customer.id}">${customer.name}</a>
            </td>
            <td>
                    ${customer.contact}
            </td>
            <td>
                    ${customer.telephone}
            </td>
            <td>
                    ${customer.email}
            </td>
            <td>
                <a href="${BASE}/customer_edit?id=${customer.id}">编辑</a>
                <a href="${BASE}/customer_delete?id=${customer.id}">删除</a>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
</body>
</html>
