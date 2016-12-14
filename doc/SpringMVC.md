    spring通过java annotation就可以注释一个类为action ，在方法上添加上一个java annotation 就可以配置请求的路径了，
    那么这种机制是如何实现的呢，今天我们使用"自定义注解+Servlet"来简单模拟一下Spring MVC中的这种注解配置方式

###编写注解
 1. Controller注解
 　  开发Controller注解，这个注解只有一个value属性，默认值为空字符串
 2. RequestMapping注解
 　　开发RequestMapping注解，用于定义请求路径，这个注解只有一个value属性，默认值为空字符串

 以上就是我们自定义的两个注解，注解的开发工作就算是完成了，有了注解之后，那么就必须针对注解来编写处理器，
 否则我们开发的注解配置到类或者方法上面是不起作用的，这里我们使用Servlet来作为注解的处理器

###编写核心的注解处理器
1. 开发AnnotationHandleServlet
　这里使用一个Servlet来作为注解处理器，编写一个AnnotationHandleServlet
  这里说一下AnnotationHandleServlet的实现思路:
  1、AnnotationHandleServlet初始化(init)时扫描指定的包下面使用了Controller注解的类
  2、遍历类中的方法，找到类中使用了RequestMapping注解标注的那些方法，获取RequestMapping注解的value属性值，
    value属性值指明了该方法的访问路径，以RequestMapping注解的value属性值作为key，Class类作为value将存储到一个静态Map集合中。
    当用户请求时(无论是get还是post请求)，会调用封装好的execute方法 ，execute会先获取请求的url，然后解析该URL，
    根据解析好的URL从Map集合中取出要调用的目标类 ，再遍历目标类中定义的所有方法，找到类中使用了RequestMapping注解的那些方法，
    判断方法上面的RequestMapping注解的value属性值是否和解析出来的URL路径一致,如果一致，说明了这个就是要调用的目标方法，
    此时就可以利用java反射机制先实例化目标类对象，然后再通过实例化对象调用要执行的方法处理用户请求.

    另外，方法处理完成之后需要给客户端发送响应信息，比如告诉客户端要跳转到哪一个页面，采用的是服务器端跳转还是客户端方式跳转，
    或者发送一些数据到客户端显示，那么该如何发送响应信息给客户端呢，在此，我们可以设计一个View(视图)类，对这些操作属性进行封装，
    其中包括跳转的路径 、展现到页面的数据、跳转方式。

2. 在Web.xml文件中注册AnnotationHandleServlet
   在web.xml文件中配置AnnotationHandleServlet和需要扫描的包

###相关代码讲解
1、BeanUtils
   BeanUtils工具类主要是用来处理一些反射的操作
2、RequestMapingMap
   该类是用于存储方法的访问路径，AnnotationHandleServlet初始化时会将类(使用Controller注解标注的那些类)中使用了
   RequestMapping注解标注的那些方法的访问路径存储到RequestMapingMap中
3、ScanClassUtil
　扫描某个包下面的类的工具类
4、WebContext
   WebContext主要是用来存储当前线程中的HttpServletRequest和HttpServletResponse，当别的地方需要使用HttpServletRequest和
   HttpServletResponse，就可以通过requestHodler和responseHodler获取，通过WebContext.java这个类 ，我们可以在作为Controller
   的普通java类中获取当前请求的request、response或者session相关请求类的实例变量，并且线程间互不干扰的，因为用到了ThreadLocal这个类。
5、View
   一个视图类，对一些客户端响应操作进行封装，其中包括跳转的路径 、展现到页面的数据、跳转方式
6、ViewData
   request范围的数据存储类，当需要发送数据到客户端显示时，就可以将要显示的数据存储到ViewData类中。
使用ViewData.put(String name,Object value)方法往request对象中存数据
7、DispatchActionConstant
   一个跳转方式的常量类