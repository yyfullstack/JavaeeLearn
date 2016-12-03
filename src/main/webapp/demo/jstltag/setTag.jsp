<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="person" class="com.yckjsoft.javaee.jstl.Person"/>
<html>
<head>
    <title>JSTL: --表达式控制标签“set”标签的使用</title>
</head>
<body>
<h3>代码给出了给指定scope范围赋值的示例。</h3>
<ul>
    <%--通过<c:set>标签将data1的值放入page范围中。--%>
    <li>把一个值放入page域中:<c:set var="data1" value="jack" scope="page"/></li>
    <%--使用EL表达式从pageScope得到data1的值。--%>
    <li>从page域中得到值：${pageScope.data1}</li>

    <%--通过<c:set>标签将data2的值放入request范围中。--%>
    <li>把一个值放入request域中:<c:set var="data2" value="lucy" scope="request"/></li>
    <%--使用EL表达式从requestScope得到data2的值。--%>
    <li>从request域中得到值：${requestScope.data2}</li>

    <%--通过<c:set>标签将值name1的值放入session范围中。--%>
    <li>把一个值放入session域中。<c:set value="苍狼" var="name1" scope="session"></c:set></li>
    <%--使用EL表达式从sessionScope得到name1的值。--%>
    <li>从session域中得到值:${sessionScope.name1} </li>

    <%--把name2放入application范围中。 --%>
    <li>把一个值放入application域中。<c:set var="name2" scope="application">白虎</c:set></li>
    <li>使用out标签和EL表达式嵌套从application域中得到值：
        <c:out value="${applicationScope.name2}">未得到name2的值</c:out>
    </li>

    <%--不指定范围使用EL自动查找得到值 --%>
    <li>未指定scope的范围，会从不同的范围内查找得到相应的值：${data1}、${data2}、${name1}、${name2}</li>

</ul>

<hr/>
<h3>使用Java脚本实现以上功能</h3>
<ul>
    <li>把一个值放入page域中。<%pageContext.setAttribute("data1", "xdp");%></li>
    <li>从page域中得到值:<%out.println(pageContext.getAttribute("data1"));%></li>

    <li>把一个值放入request域中。<%request.setAttribute("data2", "gacl");%></li>
    <li>从request域中得到值:<%out.println(request.getAttribute("data2"));%></li>

    <li>把一个值放入session域中。<%session.setAttribute("name1", "孤傲苍狼");%></li>
    <li>从session中域得到值:<%out.println(session.getAttribute("name1"));%></li>
    <%--out.println()方法与<%=%>表达式输出功能一样 但使用表达式输出（<%=%>）明显要比使用out.println()输出更好。    --%>
    <li><%=session.getAttribute("name1") %>
    </li>
    <li>把另一个值放入application域中。<%application.setAttribute("name2", "白虎神皇");%></li>
    <li> 从application域中得到值：<%out.println(application.getAttribute("name2"));%></li>
    <li><%=application.getAttribute("name2")%>
    </li>
    <li>未指定scope的范围，会从不同的范围内查找得到相应的值：
        <%=pageContext.findAttribute("data1")%>、
        <%=pageContext.findAttribute("data2")%>、
        <%=pageContext.findAttribute("name1")%>、
        <%=pageContext.findAttribute("name2")%>
    </li>
</ul>

<hr/>
<h3>操作JavaBean，设置JavaBean的属性值</h3>

<%--设置JavaBean的属性值，等同与setter方法，Target指向实例化后的对象，property指向要插入值的参数名。
      注意：使用target时一定要指向实例化后的JavaBean对象，也就是要跟<jsp:useBean>配套使用，
      也可以java脚本实例化，但这就失去了是用标签的本质意义。
      使用Java脚本实例化：
      <%@page import="javabean.Person"%
      <% Person person=new Person(); %>
       --%>
<c:set target="${person}" property="name">孤傲苍狼</c:set>
<c:set target="${person}" property="age">25</c:set>
<c:set target="${person}" property="sex">男</c:set>
<c:set target="${person}" property="home">中国</c:set>


<ul>
    <li>使用的目标对象为：${person}</li>
    <li>从Bean中获得的name值为：<c:out value="${person.name}"></c:out></li>
    <li>从Bean中获得的age值为：<c:out value="${person.age}"></c:out></li>
    <li>从Bean中获得的sex值为：<c:out value="${person.sex}"></c:out></li>
    <li>从Bean中获得的home值为：<c:out value="${person.home}"></c:out></li>
</ul>

<hr/>
<h3>操作Map</h3>
<%
    Map map = new HashMap();
    request.setAttribute("map", map);
%>
<%--将data对象的值存储到map集合中 --%>
<c:set property="data" value="gacl" target="${map}"/>
${map.data}
</body>
</html>
