    Web服务器收到客户端的http请求，会针对每一次请求，分别创建一个用于代表请求的request对象、和代表响应的response对象。
    request和response对象即然代表请求和响应，那我们要获取客户机提交过来的数据，只需要找request对象就行了。
    要向客户机输出数据，只需要找response对象就行了

###HttpServletResponse对象介绍
    HttpServletResponse对象代表服务器的响应。这个对象中封装了向客户端发送数据、发送响应头，发送响应状态码的方法

####1.1 负责向客户端（浏览器）发送数据的相关方法
    getWriter();
    getOutputStream();
####1.2 负责向客户端（浏览器）发送响应头的相关方法
    addDateHeader（String name, long date）
    addHeader(String name, String value)
    addIntHeader(String name, int value)
    containsHeader(String name)
    setDateHeader(String name, long date)
    setHeader(String name, String value)
    setIntHeader(String name, int value)
####1.3 负责向客户端（浏览器）发送响应状态码的相关方法
    setStatus(int sc)
####1.4 响应状态码的常量
    SC_NOT_FOUND 404
    SC_OK 200
    SC_INTERNAL_SERVER_ERROR 500

####1.5生成随机图片用作验证码
    生成图片主要用到了一个BufferedImage类，
####1.6 设置响应头控制浏览器的行为
 1. 设置http响应头控制浏览器禁止缓存当前文档内容
     response.setDateHeader("expries", -1);
     response.setHeader("Cache-Control", "no-cache");
     response.setHeader("Pragma", "no-cache");
 2. 设置http响应头控制浏览器定时刷新网页(refresh)
     response.setHeader("refresh", "5");//设置refresh响应头控制浏览器每隔5秒钟刷新一次
 3. 通过response实现请求重定向
     请求重定向指：一个web资源收到客户端请求后，通知客户端去访问另外一个web资源，这称之为请求重定向。
     应用场景：用户登陆，用户首先访问登录页面，登录成功后，就会跳转到某个页面，这个过程就是一个请求重定向的过程
     实现方式：response.sendRedirect(String location)，即调用response对象的sendRedirect方法实现请求重定向
    　sendRedirect内部的实现原理：使用response设置302状态码和设置location响应头实现重定向
###web工程中URL地址的推荐写法
    在JavaWeb开发中，只要是写URL地址，那么建议最好以"/"开头，也就是使用绝对路径的方式，那么这个"/"到底代表什么呢？
    可以用如下的方式来记忆"/"：如果"/"是给服务器用的，则代表当前的web工程，如果"/"是给浏览器用的，则代表webapps目录。
####1."/"代表当前web工程的常见应用场景
      ①.ServletContext.getRealPath(String path)获取资源的绝对路径
      /**
      * 1.ServletContext.getRealPath("/download/1.JPG")是用来获取服务器上的某个资源，
      * 那么这个"/"就是给服务器用的，"/"此时代表的就是web工程
      * ServletContext.getRealPath("/download/1.JPG")表示的就是读取web工程下的download文件夹中的1.JPG这个资源
      * 只要明白了"/"代表的具体含义，就可以很快写出要访问的web资源的绝对路径
      */
      this.getServletContext().getRealPath("/download/1.JPG");

      ②.在服务器端forward到其他页面
      /**
      * 2.forward
      * 客户端请求某个web资源，服务器跳转到另外一个web资源，这个forward也是给服务器用的，
      * 那么这个"/"就是给服务器用的，所以此时"/"代表的就是web工程
      */
      this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

      ③.使用include指令或者<jsp:include>标签引入页面
      <%@include file="/jspfragments/head.jspf" %>
      <jsp:include page="/jspfragments/demo.jsp" />

 ####2."/"代表webapps目录的常见应用场景
       ①.使用sendRedirect实现请求重定向
       response.sendRedirect("/index.jsp");
       服务器发送一个URL地址给浏览器，浏览器拿到URL地址之后，再去请求服务器，所以这个"/"是给浏览器使用的，此时"/"代表的就是webapps目录，
       "/index.jsp"这个地址指的就是"webapps\index.jsp"
       response.sendRedirect("/项目名称/文件夹目录/页面");这种写法是将项目名称写死在程序中的做法，不灵活，
       万一哪天项目名称变了，此时就得改程序，所以推荐使用下面的灵活写法：
        response.sendRedirect(request.getContextPath()+"/index.jsp");

        request.getContextPath()获取到的内容就是"/***"(项目名称)，这样就比较灵活了，
        使用request.getContextPath()代替"/项目名称"，推荐使用这种方式，灵活方便！

        ②.使用超链接跳转
        <a href="/JavaWeb_HttpServletResponse_Study_20140615/index.jsp">跳转到首页</a>
        这是客户端浏览器使用的超链接跳转，这个"/"是给浏览器使用的，此时"/"代表的就是webapps目录。
        使用超链接访问web资源，绝对路径的写法推荐使用下面的写法改进：
        <a href="${pageContext.request.contextPath}/index.jsp">跳转到首页</a>

        ③.Form表单提交
        <form action="/***/servlet/CheckServlet" method="post">
                 <input type="submit" value="提交">
        </form>
        对于form表单提交中action属性绝对路径的写法，也推荐使用如下的方式改进：
        <form action="${pageContext.request.contextPath}/servlet/CheckServlet" method="post">
                <input type="submit" value="提交">
        </form>

        ④.js脚本和css样式文件的引用
         <%--使用绝对路径的方式引用js脚本--%>
         <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
         <%--${pageContext.request.contextPath}与request.getContextPath()写法是得到的效果是一样的--%>
         <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
         <%--使用绝对路径的方式引用css样式--%>
         <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" type="text/css"/>


