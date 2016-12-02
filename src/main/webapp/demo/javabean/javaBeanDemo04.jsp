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
使用jsp:setProperty标签设置person对象的属性值
jsp:setProperty在设置对象的属性值时会自动把字符串转换成8种基本数据类型
但是jsp:setProperty对于复合数据类型无法自动转换
--%>
<jsp:setProperty property="name" name="person" value="白虎神皇"/>
<jsp:setProperty property="sex" name="person" value="男"/>
<jsp:setProperty property="age" name="person" value="24"/>
<jsp:setProperty property="married" name="person" value="false"/>
<%--
birthday属性是一个Date类型，这个属于复合数据类型，因此无法将字符串自动转换成Date ，用下面这种写法是会报错的
<jsp:setProperty property="birthday" name="person" value="1988-05-07"/>
--%>
<jsp:setProperty property="birthday" name="person" value="<%=new Date()%>"/>
<html>
<head>
    <title>jsp:getProperty标签使用范例</title>
</head>
<body>
<%--使用jsp:getProperty标签获取对象的属性值 --%>
<h2>姓名：<jsp:getProperty property="name" name="person"/></h2>
<h2>性别：<jsp:getProperty property="sex" name="person"/></h2>
<h2>年龄：<jsp:getProperty property="age" name="person"/></h2>
<h2>已婚：<jsp:getProperty property="married" name="person"/></h2>
<h2>出生日期：<jsp:getProperty property="birthday" name="person"/></h2>
</body>
</html>
