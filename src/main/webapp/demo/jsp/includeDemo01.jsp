<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<%!
    int i = 10;
%>
<h1>includeDemo.jsp中i的值为：<%=i %></h1>


<%--运行后发现出现了重复定义变量i的错误提示信息，因为静态包含是将全部内容包含进来之后，--%>
<%--再进行处理，属于先包含后处理。由于被包含进来的页面demo.jsp中定义了一个变量i，--%>
<%--而包含页面JspIncludeTagDemo01.jsp本身又定义了一个变量i，--%>
<%--所以服务器在处理JspIncludeTagDemo01.jsp这个页面时就会发现里面有两个重复定义的变量i，因此就会报错。--%>
<h1>
    <%@include file="jsp_fragments/include_part.jsp"%>
</h1>