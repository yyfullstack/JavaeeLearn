<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--
在jsp中使用jsp:useBean标签来实例化一个Java类的对象
<jsp:useBean id="person" class="gacl.javabean.study.Person" scope="page"/>
    ┝<jsp:useBean>：表示在JSP中要使用JavaBean。
    ┝id:表示生成的实例化对象，凡是在标签中看见了id，则肯定表示一个实例对象。
    ┝class：此对象对应的包.类名称
    ┝scope：此javaBean的保存范围，四种范围：page、request、session、application
--%>
<jsp:useBean id="person" class="com.yckjsoft.javaee.javabean.Person" scope="page"/>
<%--
    jsp:setProperty标签用所有的请求参数为bean的属性赋值
    property="*"代表bean的所有属性
    //访问方式
    http://localhost:8080/demo/javabean/javaBeanDemo03.jsp?name=jack&sex=male&age=27
--%>
<jsp:setProperty property="*" name="person"/>
<html>
<head>
    <title>jsp:setProperty标签使用范例</title>
</head>
<body>
<%--使用getXxx()方法获取对象的属性值 --%>
<h2>姓名：<%=person.getName()%></h2>
<h2>性别：<%=person.getSex()%></h2>
<h2>年龄：<%=person.getAge()%></h2>
</body>
</html>
