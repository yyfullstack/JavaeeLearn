###Servlet 简介
    Servelt是sun公司提供的一门开发动态web资源的技术。
    Sun公司在其API中提供了一个servlet接口，用户若想用发一个动态web资源(即开发一个Java程序向浏览器输出数据)，需要完成以下2个步骤：
    1、编写一个Java类，实现servlet接口。
    2、把开发好的Java类部署到web服务器中。
    按照一种约定俗成的称呼习惯，通常我们也把实现了servlet接口的java程序，称之为Servlet

###Servlet 运行过程
    Servlet程序是由WEB服务器调用，web服务器收到客户端的Servlet访问请求后
    ①Web服务器首先检查是否已经装载并创建了该Servlet的实例对象。如果是，则直接执行第④步，否则，执行第②步。
    ②装载并创建该Servlet的一个实例对象。
    ③调用Servlet实例对象的init()方法。
    ④创建一个用于封装HTTP请求消息的HttpServletRequest对象和一个代表HTTP响应消息的HttpServletResponse对象，
    然后调用Servlet的service()方法并将请求和响应对象作为参数传递进去。
    ⑤WEB应用程序被停止或重新启动之前，Servlet引擎将卸载Servlet，并在卸载之前调用Servlet的destroy()方法。

#### Servlet接口实现类
    Servlet接口SUN公司定义了两个默认实现类，分别为：GenericServlet、HttpServlet。
    HttpServlet指能够处理HTTP请求的servlet，它在原有Servlet接口上添加了一些与HTTP协议处理方法，
    它比Servlet接口的功能更为强大。因此开发人员在编写Servlet时，通常应继承这个类，而避免直接去实现Servlet接口。

    HttpServlet在实现Servlet接口时，覆写了service方法，该方法体内的代码会自动判断用户的请求方式，如为GET请求，
    则调用HttpServlet的doGet方法，如为Post请求，则调用doPost方法。因此，开发人员在编写Servlet时，
    通常只需要覆写doGet或doPost方法，而不要去覆写service方法。

####Servlet访问URL映射配置
    由于客户端是通过URL地址访问web服务器中的资源，所以Servlet程序若想被外界访问，
    必须把servlet程序映射到一个URL地址上，这个工作在web.xml文件中使用<servlet>元素和<servlet-mapping>元素完成。
    <servlet>元素用于注册Servlet，它包含有两个主要的子元素：<servlet-name>和<servlet-class>，
    分别用于设置Servlet的注册名称和Servlet的完整类名。 一个<servlet-mapping>元素用于映射一个
    已注册的Servlet的一个对外访问路径，它包含有两个子元素：<servlet-name>和<url-pattern>，
    分别用于指定Servlet的注册名称和Servlet的对外访问路径

####Servlet访问URL使用*通配符映射　　
    在Servlet映射到的URL中也可以使用*通配符，但是只能有两种固定的格式：
    一种格式是"*.扩展名"，另一种格式是以正斜杠（/）开头并以"/*"结尾
    匹配的原则就是"谁长得更像就找谁"

####Servlet与普通Java类的区别
    Servlet是一个供其他Java程序（Servlet引擎）调用的Java类，它不能独立运行，它的运行完全由Servlet引擎来控制和调度。
    针对客户端的多次Servlet请求，通常情况下，服务器只会创建一个Servlet实例对象，也就是说Servlet实例对象一旦创建，
    它就会驻留在内存中，为后续的其它请求服务，直至web容器退出，servlet实例对象才会销毁。
    在Servlet的整个生命周期内，Servlet的init方法只被调用一次。而对一个Servlet的每次访问请求都导致Servlet引擎
    调用一次servlet的service方法。对于每次访问请求，Servlet引擎都会创建一个新的HttpServletRequest请求对象和
    一个新的HttpServletResponse响应对象，然后将这两个对象作为参数传递给它调用的Servlet的service()方法，
    service方法再根据请求方式分别调用doXXX方法

    如果在<servlet>元素中配置了一个<load-on-startup>元素，那么WEB应用程序在启动时，就会装载并创建Servlet的实例对象、
    以及调用Servlet实例对象的init()方法。

####缺省Servlet
    如果某个Servlet的映射路径仅仅为一个正斜杠（/），那么这个Servlet就成为当前Web应用程序的缺省Servlet。
    凡是在web.xml文件中找不到匹配的<servlet-mapping>元素的URL，它们的访问请求都将交给缺省Servlet处理，
    也就是说，缺省Servlet用于处理所有其他Servlet都不处理的访问请求

    在<tomcat的安装目录>\conf\web.xml文件中，
    注册了一个名称为org.apache.catalina.servlets.DefaultServlet的Servlet，并将这个Servlet设置为了缺省Servlet
    当访问Tomcat服务器中的某个静态HTML文件和图片时，实际上是在访问这个缺省Servlet。
####Servlet的线程安全问题
    当多个客户端并发访问同一个Servlet时，web服务器会为每一个客户端的访问请求创建一个线程，
    并在这个线程上调用Servlet的service方法，因此service方法内如果访问了同一个资源的话，就有可能引发线程安全问题

    线程安全问题只存在多个线程并发操作同一个资源的情况下，所以在编写Servlet的时候，如果并发访问某一个资源(变量，集合等)，
    就会存在线程安全问题，那么该如何解决这个问题呢

    现在这种做法是给Servlet对象加了一把锁，保证任何时候都只有一个线程在访问该Servlet对象里面的资源，这样就不存在线程安全问题了
    这种做法虽然解决了线程安全问题，但是编写Servlet却万万不能用这种方式处理线程安全问题，假如有9999个人同时访问这个Servlet，
    那么这9999个人必须按先后顺序排队轮流访问。
    针对Servlet的线程安全问题，Sun公司是提供有解决方案的：让Servlet去实现一个SingleThreadModel接口，
    如果某个Servlet实现了SingleThreadModel接口，那么Servlet引擎将以单线程模式来调用其service方法。
    查看Sevlet的API可以看到，SingleThreadModel接口中没有定义任何方法和常量，在Java中，把没有定义任何方法和常量的接口称之为标记接口，经常看到的一个最典型的标记接口就是"Serializable"，这个接口也是没有定义任何方法和常量的，
    标记接口在Java中有什么用呢？主要作用就是给某个对象打上一个标志，告诉JVM，这个对象可以做什么，比如实现了"Serializable"接口的类的对象就可以被序列化，还有一个"Cloneable"接口，这个也是一个标记接口，在默认情况下，Java中的对象是不允许被克隆的，就像现实生活中的人一样，不允许克隆，但是只要实现了"Cloneable"接口，那么对象就可以被克隆了。

    让Servlet实现了SingleThreadModel接口，只要在Servlet类的定义中增加实现SingleThreadModel接口的声明即可。
    对于实现了SingleThreadModel接口的Servlet，Servlet引擎仍然支持对该Servlet的多线程并发访问，
    其采用的方式是产生多个Servlet实例对象，并发的每个线程分别调用一个独立的Servlet实例对象。
    实现SingleThreadModel接口并不能真正解决Servlet的线程安全问题，因为Servlet引擎会创建多个Servlet实例对象，
    而真正意义上解决多线程安全问题是指一个Servlet实例对象被多个线程同时调用的问题。事实上，在Servlet API 2.4中，已经将SingleThreadModel标记为Deprecated（过时的）。

###ServletConfig讲解
####1.1配置Servlet初始化参数
    在Servlet的配置文件web.xml中，可以使用一个或多个<init-param>标签为servlet配置一些初始化参数。

####1.2通过ServletConfig获取Servlet的初始化参数
    当servlet配置了初始化参数后，web容器在创建servlet实例对象时，会自动将这些初始化参数封装到ServletConfig对象中，
    并在调用servlet的init方法时，将ServletConfig对象传递给servlet。进而，我们通过ServletConfig对象就可以得到
    当前servlet的初始化参数信息

###ServletContext对象
    WEB容器在启动时，它会为每个WEB应用程序都创建一个对应的ServletContext对象，它代表当前web应用。
    ServletConfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，可以通过ServletConfig.getServletContext方法
    获得ServletContext对象。
    由于一个WEB应用中的所有Servlet共享同一个ServletContext对象，因此Servlet对象之间可以通过ServletContext对象来实现通讯。
    ServletContext对象通常也被称之为context域对象