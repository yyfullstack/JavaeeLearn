package com.smart4j.chapter2.web.filter;


import com.smart4j.chapter2.util.JdbcUtils_DBCP;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by yong on 2016/11/21 0021.
 * ThreadLocal + Filter 统一处理数据库事务
 */

//@WebFilter(filterName="transactionFilter",urlPatterns="/*")
public class TransactionFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Connection conn = null;
        try {
            //1.获取数据库连接对象Connection
            conn = JdbcUtils_DBCP.getConnection();
            //2.开启事务
            conn.setAutoCommit(false);
            //3、利用ThreadLocal把获取数据库连接对象Connection和当前线程绑定
            ConnectionContext.getInstance().bind(conn);
            //4、把请求转发给目标Servlet
            filterChain.doFilter(servletRequest, servletResponse);
            //5、提交事务
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //6、回滚事务
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            //req.setAttribute("errMsg", e.getMessage());
            //req.getRequestDispatcher("/error.jsp").forward(req, res);

            //出现异常之后跳转到错误页面
            resp.sendRedirect("/error.jsp");
        } finally {
            //7.解除绑定
            ConnectionContext.getInstance().remove();
            //8、关闭数据库连接
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void destroy() {

    }
}
