<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/10/26 0026
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>pageContext访问其它域</title>
</head>
    <%
    //此时相当于往session对象中存放了一个name属性，等价于 session.setAttribute("name","孤傲苍狼");
    pageContext.setAttribute("name","孤傲苍狼",PageContext.SESSION_SCOPE);
    %>
    <%
    //取得session对象的属性，使用pageContext对象获取
    String refName1 = (String)pageContext.getAttribute("name",PageContext.SESSION_SCOPE);
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
     String refName2 = (String)session.getAttribute("name");
    %>
<h1>取出存放在session对象中的属性值：</h1>
<p>第一种做法：使用pageContext.getAttribute("attributeName",PageContext.SESSION_SCOPE);去取出session对象中值</p>
<h3>姓名：<%=refName1%>
</h3>
<p>第二种做法：使用session.getAttribute("attributeName");去取出session对象中值</p>
<h3>姓名：<%=refName2%>
</h3>
