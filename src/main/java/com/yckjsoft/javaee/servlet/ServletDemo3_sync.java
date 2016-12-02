package com.yckjsoft.javaee.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 * 现在这种做法是给Servlet对象加了一把锁，保证任何时候都只有一个线程在访问该Servlet对象里面的资源，这样就不存在线程安全问题了
 */
public class ServletDemo3_sync extends HttpServlet {
    int i = 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 加了synchronized后，并发访问i时就不存在线程安全问题了，
         * 为什么加了synchronized后就没有线程安全问题了呢？
         * 假如现在有一个线程访问Servlet对象，那么它就先拿到了Servlet对象的那把锁
         * 等到它执行完之后才会把锁还给Servlet对象，由于是它先拿到了Servlet对象的那把锁，
         * 所以当有别的线程来访问这个Servlet对象时，由于锁已经被之前的线程拿走了，后面的线程只能排队等候了
         *
         */
        ///在java中，每一个对象都有一把锁，这里的this指的就是Servlet对象
        synchronized (this) {
            i++;
            try {
                Thread.sleep(1000 * 4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resp.getWriter().write(i + "");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
