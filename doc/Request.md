###HttpServletRequest介绍
    HttpServletRequest对象代表客户端的请求，当客户端通过HTTP协议访问服务器时，
    HTTP请求头中的所有信息都封装在这个对象中，通过这个对象提供的方法，可以获得客户端请求的所有信息。
###Request常用方法
####2.1、获得客户机信息
    getRequestURL方法返回客户端发出请求时的完整URL。
    getRequestURI方法返回请求行中的资源名部分。
    getQueryString 方法返回请求行中的参数部分。
    getPathInfo方法返回请求URL中的额外路径信息。
    额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
    getRemoteAddr方法返回发出请求的客户机的IP地址。
    getRemoteHost方法返回发出请求的客户机的完整主机名。
    getRemotePort方法返回客户机所使用的网络端口号。
    getLocalAddr方法返回WEB服务器的IP地址。
    getLocalName方法返回WEB服务器的主机名。

####2.2、获得客户机请求头
    getHeader(string name)方法:String
    getHeaders(String name)方法:Enumeration
    getHeaderNames()方法

####2.3、获得客户机请求参数(客户端提交的数据)
    getParameter(String)方法(常用)
    getParameterValues(String name)方法(常用)
    getParameterNames()方法(不常用)
    getParameterMap()方法(编写框架时常用)

###request接收表单提交中文参数乱码问题
####3.1 以POST方式提交表单中文参数的乱码问题
####3.2 POST方式提交中文数据乱码产生的原因和解决办法
    之所以会产生乱码，就是因为服务器和客户端沟通的编码不一致造成的，因此解决的办法是：
    在客户端和服务器之间设置一个统一的编码，之后就按照此编码进行数据的传输和接收。
    由于客户端是以UTF-8字符编码将表单数据传输到服务器端的，因此服务器也需要设置以UTF-8字符编码进行接收，要想完成此操作，
    服务器可以直接使用从ServletRequest接口继承而来的"setCharacterEncoding(charset)"方法进行统一的编码设置

####3.3以GET方式提交表单中文参数的乱码问题
    那么这个中文乱码问题又该如何解决呢，是否可以通过request.setCharacterEncoding("UTF-8");设置服务器以UTF-8的编码进行
    接收这种方式来解决中文乱码问题呢，注意，对于以get方式传输的中文数据，通过request.setCharacterEncoding("UTF-8");
    这种方式是解决不了中文乱码问题

####3.4 get方式提交中文数据乱码产生的原因和解决办法
    request即使设置了以指定的编码接收数据也是无效的(至于为什么无效我也没有弄明白)，默认的还是使用ISO8859-1这个字符编码来
    接收数据，客户端以UTF-8的编码传输数据到服务器端，而服务器端的request对象使用的是ISO8859-1这个字符编码来接收数据，
    服务器和客户端沟通的编码不一致因此才会产生中文乱码的。解决办法：在接收到数据后，先获取request对象以ISO8859-1字符编码
    接收到的原始数据的字节数组，然后通过字节数组以指定的编码构建字符串，解决乱码问题

####3.5 以超链接形式传递中文参数的乱码问题
    点击超链接，数据是以get的方式传输到服务器的，所以接收中文数据时也会产生中文乱码问题，而解决中文乱码问题的
    方式与上述的以get方式提交表单中文数据乱码处理问题的方式一致

    需要提的一点就是URL地址后面如果跟了中文数据，那么中文参数最好使用URL编码进行处理


###Request对象实现请求转发
####4.1请求转发的基本概念
    　请求转发：指一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理。
    　请求转发的应用场景：MVC设计模式
  在Servlet中实现请求转发的两种方式：
1. 通过ServletContext的getRequestDispatcher(String path)方法，该方法返回一个RequestDispatcher对象，
调用这个对象的forward方法可以实现请求转发。
2. 通过request对象提供的getRequestDispatche(String path)方法，该方法返回一个RequestDispatcher对象，
调用这个对象的forward方法可以实现请求转发。

####4.2 请求重定向和请求转发的区别
    一个web资源收到客户端请求后，通知服务器去调用另外一个web资源进行处理，称之为请求转发/307。
    一个web资源收到客户端请求后，通知浏览器去访问另外一个web资源进行处理，称之为请求重定向/302。