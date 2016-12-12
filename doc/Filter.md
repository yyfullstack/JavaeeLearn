###Filter简介
    Filter也称之为过滤器，它是Servlet技术中最激动人心的技术，WEB开发人员通过Filter技术，对web服务器管理的所有web资源：
    例如Jsp, Servlet, 静态图片文件或静态 html 文件等进行拦截，从而实现一些特殊的功能。例如实现URL级别的权限访问控制、
    过滤敏感词汇、压缩响应信息等一些高级功能。
    Servlet API中提供了一个Filter接口，开发web应用时，如果编写的Java类实现了这个接口，则把这个java类称之为过滤器Filter。
    通过Filter技术，开发人员可以实现用户在访问某个目标资源之前，对访问的请求和响应进行拦截

###Filter是如何实现拦截的？
    Filter接口中有一个doFilter方法，当我们编写好Filter，并配置对哪个web资源进行拦截后，WEB服务器每次在调用web资源
    的service方法之前，都会先调用一下filter的doFilter方法，因此，在该方法内编写代码可达到如下目的：

    调用目标资源之前，让一段代码执行。 是否调用目标资源（即是否让用户访问web资源）。 调用目标资源之后，让一段代码执行。
 　　web服务器在调用doFilter方法时，会传递一个filterChain对象进来，filterChain对象是filter接口中最重要的一个对 象，
 它也提供了一个doFilter方法，开发人员可以根据需求决定是否调用此方法，调用该方法，则web服务器就会调用web资源的service方 法，
 即web资源就会被访问，否则web资源不会被访问。

###Filter开发入门
   1. Filter开发步骤
   　　Filter开发分为二个步骤：
   编写java类实现Filter接口，并实现其doFilter方法。
   在 web.xml 文件中使用<filter>和<filter-mapping>元素对编写的filter类进行注册，并设置它所能拦截的资源。
   2. Filter链
   　　在一个web应用中，可以开发编写多个Filter，这些Filter组合起来称之为一个Filter链。
   　　web服务器根据Filter在web.xml文件中的注册顺序，决定先调用哪个Filter，当第一个Filter的doFilter方法被调用时，
   web服务器会创建一个代表Filter链的FilterChain对象传递给该方法。在doFilter方法中，开发人员如果调用了FilterChain对象
   的doFilter方法，则web服务器会检查FilterChain对象中是否还有filter，如果有，则调用第2个filter，如果没有，则调用目标资源。

###Filter的生命周期
  1. Filter的创建
   　Filter的创建和销毁由WEB服务器负责。 web 应用程序启动时，web 服务器将创建Filter 的实例对象，并调用其init方法，
   完成对象的初始化功能，从而为后续的用户请求作好拦截的准备工作，filter对象只会创建一次，init方法也只会执行一次。
   通过init方法的参数，可获得代表当前filter配置信息的FilterConfig对象。
  2. Filter的销毁
     Web容器调用destroy方法销毁Filter。destroy方法在Filter的生命周期中仅执行一次。在destroy方法中，可以释放过滤器使用的资源。
  3. FilterConfig接口
     用户在配置filter时，可以使用<init-param>为filter配置一些初始化参数，当web容器实例化Filter对象，调用其init方法时，会把封装了filter初始化参数的filterConfig对象传递进来。因此开发人员在编写filter时，通过filterConfig对象的方法，就可获得：
     String getFilterName()：得到filter的名称。
     String getInitParameter(String name)： 返回在部署描述中指定名称的初始化参数的值。如果不存在返回null.
     Enumeration getInitParameterNames()：返回过滤器的所有初始化参数的名字的枚举集合。
     public ServletContext getServletContext()：返回Servlet上下文对象的引用。
 ###Filter的部署
    Filter的部署分为两个步骤：
    1、注册Filter
    2、映射Filter
1. 注册Filter
    开发好Filter之后，需要在web.xml文件中进行注册，这样才能够被web服务器调用
    <description>用于添加描述信息，该元素的内容可为空，<description>可以不配置。
    <filter-name>用于为过滤器指定一个名字，该元素的内容不能为空。
    <filter-class>元素用于指定过滤器的完整的限定类名。
    <init-param>元素用于为过滤器指定初始化参数，它的子元素<param-name>指定参数的名字，<param-value>指定参数的值。
    在过滤器中，可以使用FilterConfig接口对象来访问初始化参数。如果过滤器不需要指定初始化参数，那么<init-param>元素可以不配置。
2. 映射Filter
　　在web.xml文件中注册了Filter之后，还要在web.xml文件中映射Filter
    <filter-mapping>元素用于设置一个 Filter 所负责拦截的资源。一个Filter拦截的资源可通过两种方式来指定：
    Servlet 名称和资源访问的请求路径
　　<filter-name>子元素用于设置filter的注册名称。该值必须是在<filter>元素中声明过的过滤器的名字
　　<url-pattern>设置 filter 所拦截的请求路径(过滤器关联的URL样式)
　　<servlet-name>指定过滤器所拦截的Servlet名称。
　　<dispatcher>指定过滤器所拦截的资源被 Servlet 容器调用的方式，可以是REQUEST,INCLUDE,FORWARD和ERROR之一，默认REQUEST。
3. <dispatcher> 子元素可以设置的值及其意义：
   REQUEST：当用户直接访问页面时，Web容器将会调用过滤器。如果目标资源是通过RequestDispatcher的include()或forward()方法访问时，
   那么该过滤器就不会被调用。
   INCLUDE：如果目标资源是通过RequestDispatcher的include()方法访问时，那么该过滤器将被调用。除此之外，该过滤器不会被调用。
   FORWARD：如果目标资源是通过RequestDispatcher的forward()方法访问时，那么该过滤器将被调用，除此之外，该过滤器不会被调用。
   ERROR：如果目标资源是通过声明式异常处理机制调用时，那么该过滤器将被调用。除此之外，过滤器不会被调用。

###Filter高级开发
    在filter中可以得到代表用户请求和响应的request、response对象，因此在编程中可以使用Decorator(装饰器)模式对request、
    response对象进行包装，再把包装对象传给目标资源，从而实现一些特殊需求。

### Decorator设计模式

####1、Decorator设计模式介绍
　　当某个对象的方法不适应业务需求时，通常有2种方式可以对方法进行增强：编写子类，覆盖需增强的方法。
使用Decorator设计模式对方法进行增强。
　　在阎宏博士的《JAVA与模式》一书中开头是这样描述装饰（Decorator）模式的：装饰模式又名包装(Wrapper)模式。
装饰模式以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案。装饰模式是在不必改变原类文件和使用继承的情况下，
动态的扩展一个对象的功能。它是通过创建一个包装对象，也就是装饰来包裹真实的对象。
　　那么在实际应用中遇到需增强对象的方法时，到底选用哪种方式比较好呢？这个没有具体的定式，只能是根据具体的需求来采用
具体的方式，不过有一种情况下，必须使用Decorator设计模式：即被增强的对象，开发人员只能得到它的对象，无法得到它的class文件。
比如request、response对象，开发人员之所以在servlet中能通过sun公司定义的HttpServletRequest\response接口去操作这些对象，
是因为Tomcat服务器厂商编写了request、response接口的实现类。web服务器在调用servlet时，会用这些接口的实现类创建出对象，
然后传递给servlet程序。此种情况下，由于开发人员根本不知道服务器厂商编写的request、response接口的实现类是哪个？
在程序中只能拿到服务器厂商提供的对象，因此就只能采用Decorator设计模式对这些对象进行增强。

####2、Decorator设计模式的实现
　　1.首先看需要被增强对象继承了什么接口或父类，编写一个类也去继承这些接口或父类。
　　2.在类中定义一个变量，变量类型即需增强对象的类型。
　　3.在类中定义一个构造函数，接收需增强的对象。
　　4.覆盖需增强的方法，编写增强的代码。

###使用Decorator设计模式增强request对象
    Servlet API 中提供了一个request对象的Decorator设计模式的默认实现类HttpServletRequestWrapper，HttpServletRequestWrapper
    类实现了request 接口中的所有方法，但这些方法的内部实现都是仅仅调用了一下所包装的的 request 对象的对应方法，
    以避免用户在对request对象进行增强时需要实现request接口中的所有方法。
 1. 使用Decorator模式包装request对象解决get和post请求方式下的中文乱码问题
 2. 使用Decorator模式包装request对象实现html标签转义功能
 3. 使用Decorator模式包装request对象实现敏感字符过滤功能

###使用Decorator设计模式增强response对象
    Servlet  API 中提供了response对象的Decorator设计模式的默认实现类HttpServletResponseWrapper ，HttpServletResponseWrapper类实现了
    response接口中的所有方法，但这些方法的内部实现都是仅仅调用了一下所包装的的 response对象的对应方法，以避免用户在对response对象
    进行增强时需要实现response接口中的所有方法。
1. response增强案例——压缩响应正文内容
   应用HttpServletResponseWrapper对象，压缩响应正文内容。
   具体思路:通过filter向目标页面传递一个自定义的response对象。在自定义的response对象中，重写getOutputStream方法和getWriter方法，
   使目标资源调用此方法输出页面内容时，获得的是我们自定义的ServletOutputStream对象。在我们自定义的ServletOuputStream对象中，
   重写write方法，使写出的数据写出到一个buffer中。当页面完成输出后，在filter中就可得到页面写出的数据，从而我们可以调用
   GzipOuputStream对数据进行压缩后再写出给浏览器，以此完成响应正文件压缩功能。
2. response增强案例——缓存数据到内存
   对于页面中很少更新的数据，例如商品分类，为避免每次都要从数据库查询分类数据，因此可把分类数据缓存在内存或文件中，
   以此来减轻数据库压力，提高系统响应速度。

###Filter(过滤器)常见应用

1. 统一全站字符编码
　　通过配置参数charset指明使用何种字符编码,以处理Html Form请求参数的中文问题
2. 禁止浏览器缓存所有动态页面
   有3 个HTTP 响应头字段都可以禁止浏览器缓存当前页面，它们在 Servlet 中的示例代码如下：
  response.setDateHeader("Expires",-1);
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Pragma","no-cache");
  并不是所有的浏览器都能完全支持上面的三个响应头，因此最好是同时使用上面的三个响应头。
  Expires数据头：值为GMT时间值，为-1指浏览器不要缓存页面
  Cache-Control响应头有两个常用值：
  no-cache指浏览器不要缓存当前页面。
  max-age:xxx指浏览器缓存页面xxx秒。
3. 控制浏览器缓存页面中的静态资源
   有些动态页面中引用了一些图片或css文件以修饰页面效果，这些图片和css文件经常是不变化的，所以为减轻服务器的压力，
   可以使用filter控制浏览器缓存这些文件，以提升服务器的性能。
4. 实现用户自动登陆
    思路是这样的：
   　　1、在用户登陆成功后，发送一个名称为user的cookie给客户端，cookie的值为用户名和md5加密后的密码。
   　　2、编写一个AutoLoginFilter，这个filter检查用户是否带有名称为user的cookie来，如果有，则调用dao查询cookie的用户名和
   密码是否和数据库匹配，匹配则向session中存入user对象（即用户登陆标记），以实现程序完成自动登陆。