package com.yckjsoft.javaee.servlet3.demo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: AnnotationHandleFilter
 * @Description: 使用Filter作为注解的处理器
 */
public class AnnotationHandleFilter implements Filter {

    private ServletContext servletContext = null;

    /* 过滤器初始化时扫描指定的包下面使用了WebServlet注解的那些类
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---AnnotationHandleFilter过滤器初始化开始---");
        servletContext = filterConfig.getServletContext();
        Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
        //获取web.xml中配置的要扫描的包
        String basePackage = filterConfig.getInitParameter("basePackage");
        //如果配置了多个包，例如：<param-value>com.yckjsoft.javaee.servlet3.controller,com.yckjsoft.javaee.servlet3.web.UI</param-value>
        if (basePackage.indexOf(",") > 0) {
            //按逗号进行分隔
            String[] packageNameArr = basePackage.split(",");
            for (String packageName : packageNameArr) {
                addServletClassToServletContext(packageName, classMap);
            }
        } else {
            addServletClassToServletContext(basePackage, classMap);
        }

        System.out.println("----AnnotationHandleFilter过滤器初始化结束---");
    }

    /**
     * @param packageName
     * @param classMap
     * @Method: addServletClassToServletContext
     * @Description:  添加ServletClass到ServletContext中
     */
    private void addServletClassToServletContext(String packageName, Map<String, Class<?>> classMap) {
        Set<Class<?>> setClasses = ScanClassUtil.getClasses(packageName);
        for (Class<?> clazz : setClasses) {
            if (clazz.isAnnotationPresent(WebServlet.class)) {
                //获取WebServlet这个Annotation的实例
                WebServlet annotationInstance = clazz.getAnnotation(WebServlet.class);
                //获取Annotation的实例的value属性的值
                String annotationAttrValue = annotationInstance.value();
                if (!annotationAttrValue.equals("")) {
                    classMap.put(annotationAttrValue, clazz);
                }
                //获取Annotation的实例的urlPatterns属性的值
                String[] urlPatterns = annotationInstance.urlPatterns();
                for (String urlPattern : urlPatterns) {
                    classMap.put(urlPattern, clazz);
                }
                servletContext.setAttribute("servletClassMap", classMap);
                System.out.println("annotationAttrValue：" + annotationAttrValue);
                String targetClassName = annotationAttrValue.substring(annotationAttrValue.lastIndexOf("/") + 1);
                System.out.println("targetClassName：" + targetClassName);
                System.out.println(clazz);
            }
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        System.out.println("---进入注解处理过滤器---");
        //将ServletRequest强制转换成HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        Map<String, Class<?>> classMap = (Map<String, Class<?>>) servletContext.getAttribute("servletClassMap");
        //获取contextPath
        String contextPath = req.getContextPath();
        //获取用户请求的URI资源
        String uri = req.getRequestURI();
        //如果没有指明要调用Servlet类中的哪个方法
        if (uri.indexOf("!") == -1) {
            //获取用户使用的请求方式
            String reqMethod = req.getMethod();
            //获取要请求的servlet路径
            String requestServletName = uri.substring(contextPath.length(), uri.lastIndexOf("."));
            //获取要使用的类
            Class<?> clazz = classMap.get(requestServletName);
            //创建类的实例
            Object obj = null;
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            Method targetMethod = null;
            if (reqMethod.equalsIgnoreCase("get")) {
                try {
                    targetMethod = clazz.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    targetMethod = clazz.getDeclaredMethod("doPost", HttpServletRequest.class, HttpServletResponse.class);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }

            try {
                //调用对象的方法进行处理
                targetMethod.invoke(obj, req, res);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            //获取要请求的servlet路径
            String requestServletName = uri.substring(contextPath.length(), uri.lastIndexOf("!"));
            //获取要调用的servlet的方法
            String invokeMethodName = uri.substring(uri.lastIndexOf("!") + 1, uri.lastIndexOf("."));

            //获取要使用的类
            Class<?> clazz = classMap.get(requestServletName);
            //创建类的实例
            Object obj = null;
            try {
                obj = clazz.newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            //获得clazz类定义的所有方法
            Method[] methods = clazz.getDeclaredMethods();
            //获取WebServlet这个Annotation的实例
            WebServlet annotationInstance = clazz.getAnnotation(WebServlet.class);
            //获取注解上配置的初始化参数数组
            WebInitParam[] initParamArr = annotationInstance.initParams();
            Map<String, String> initParamMap = new HashMap<String, String>();
            for (WebInitParam initParam : initParamArr) {
                initParamMap.put(initParam.paramName(), initParam.paramValue());
            }
            //遍历clazz类中的方法
            for (Method method : methods) {
                //该方法的返回类型
                Class<?> retType = method.getReturnType();
                //获得方法名
                String methodName = method.getName();
                //打印方法修饰符
                System.out.print(Modifier.toString(method.getModifiers()));
                System.out.print(" " + retType.getName() + " " + methodName + "(");
                //获得一个方法参数数组（getparameterTypes用于返回一个描述参数类型的Class对象数组）
                Class<?>[] paramTypes = method.getParameterTypes();
                for (int j = 0; j < paramTypes.length; j++) {
                    //如果有多个参数，中间则用逗号隔开，否则直接打印参数
                    if (j > 0) {
                        System.out.print(",");
                    }
                    System.out.print(paramTypes[j].getName());
                }
                System.out.println(");");
                if (method.getName().equalsIgnoreCase("init")) {
                    try {
                        //调用Servlet的初始化方法
                        method.invoke(obj, initParamMap);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            //获取WebServlet这个Annotation的实例
            System.out.println("invokeMethodName：" + invokeMethodName);
            try {
                try {
                    //利用反射获取方法实例，方法的签名必须符合：
                    //public void 方法名(HttpServletRequest request, HttpServletResponse response)
                    //例如：public void loginHandle(HttpServletRequest request, HttpServletResponse response)
                    Method targetMethod = clazz.getDeclaredMethod(invokeMethodName, HttpServletRequest.class, HttpServletResponse.class);
                    //调用对象的方法进行处理
                    targetMethod.invoke(obj, req, res);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy() {

    }
}