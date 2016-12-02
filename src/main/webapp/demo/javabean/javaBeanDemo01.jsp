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
<%--<%
    person.setName("任我行");
    person.setSex("男");
    person.setAge(24);
    person.setMarried(false);
%>--%>

<%--
使用jsp:setProperty标签设置person对象的属性值
jsp:setProperty在设置对象的属性值时会自动把字符串转换成8种基本数据类型
但是jsp:setProperty对于复合数据类型无法自动转换
--%>
<jsp:setProperty name="person" property="name" value="东方日发放"/>
<jsp:setProperty name="person" property="sex" value="男"/>
<jsp:setProperty name="person" property="age" value="25"/>
<jsp:setProperty name="person" property="married" value="false"/>
 <%--
birthday属性是一个Date类型，这个属于复合数据类型，因此无法将字符串自动转换成Date ，用下面这种写法是会报错的
<jsp:setProperty name="person" property="birthday" value="2012-02-12"/>
--%>
<jsp:setProperty name="person" property="birthday" value="<%=new Date()%>"/>
<html>
<head>
    <title>jsp:useBean标签使用范例</title>
</head>
<body>
<%--使用getXxx()方法获取对象的属性值 --%>
<h2>姓名：<%=person.getName()%>
</h2>
<h2>性别：<%=person.getSex()%>
</h2>
<h2>年龄：<%=person.getAge()%>
</h2>
<h2>已婚：<%=person.isMarried()%>
</h2>
<h2>生日：<%=person.getBirthday()%>
</h2>

<%--使用jsp:getProperty标签获取对象的属性值 --%>
<h2>姓名：<jsp:getProperty name="person" property="name" />
</h2>
<h2>性别：<jsp:getProperty name="person" property="sex" />
</h2>
<h2>年龄：<jsp:getProperty name="person" property="age" />
</h2>
<h2>已婚：<jsp:getProperty name="person" property="married" />
</h2>
<h2>生日：<jsp:getProperty name="person" property="birthday" />
</h2>
</body>
</html>
