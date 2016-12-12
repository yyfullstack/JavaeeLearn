###Servlet的传统配置方式
   在JavaWeb开发中， 每次编写一个Servlet都需要在web.xml文件中进行配置
   每开发一个Servlet，都要在web.xml中配置Servlet才能够使用，这实在是很头疼的事情，所以Servlet3.0之后提供了注解(annotation)，
   使得不再需要在web.xml文件中进行Servlet的部署描述，简化开发流程。本文所讲的基于注解方式配置Servlet不是针对Servlet3.0的，
   而是基于Servlet2.5的，通过开发自定义注解和注解处理器来实现类似于Servlet3.0的注解方式配置Servlet。

###基于注解的方式配置Servlet
   JDK1. 5版本之后， JAVA提供了一种叫做Annotation的新数据类型，中文译为注解或标注，它的出现为铺天盖地的XML配置文件提供了
   一个完美的解决方案，让 JAVA EE开发更加方便快速，也更加干净了。不过Servlet2.5默认情况下是不支持注解方式的配置的，
   但是我们可以开发自定义注解，然后将注解标注到Servlet上，再针对我们自定义的注解写一个注解处理器，具体的做法如下：
   1. 开发用于配置Servlet的相关注解
        1>开发WebServlet注解，用于标注处理请求的Servlet类
        将Servlet在web.xml中的配置信息使用WebServlet注解来表示，使用注解后，只需要在相应Servlet 类的前面使用类似
        @WebServlet("/servlet/LoginServlet") 注解就可以达到和上述 web.xml 文件中配置信息一样的目的。
        注解@WebServlet中的属性值"/servlet/LoginServlet"表示了web.xml 配置文件中 <servlet-mapping> 元素的子元素 <url-pattern> 里的值。
        通过这样的注解能简化在 XML 文件中配置 Servlet 信息，整个配置文件将会非常简洁干净，开发人员的工作也将大大减少
        2>开发WebInitParam注解，用于配置Servlet初始化时使用的参数
   2. 编写处理注解的处理器
       上面简要地介绍了注解的定义声明与使用方式，注解在后台需要一个处理器才能起作用，所以还得针对上面的注解编写处理器，
       在这里我们使用Filter作为注解的处理器，编写一个AnnotationHandleFilter
       AnnotationHandleFilter过滤器初始化时扫描指定的包下面使用了WebServlet注解的那些类，然后将类存储到一个Map集合中，
       再将Map集合存储到servletContext对象中