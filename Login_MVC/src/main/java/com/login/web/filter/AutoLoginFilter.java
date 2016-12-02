package com.login.web.filter;



import com.login.domain.User;
import com.login.service.IUserService;
import com.login.service.impl.UserServiceImpl;
import com.login.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/11/23 0023.
 */
public class AutoLoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //如果已经登录了，就直接chain.doFilter(request, response)放行
        if (req.getSession().getAttribute("user") != null) {
            filterChain.doFilter(req, resp);
            return;
        }
        //1.得到用户带过来的authlogin的cookie
        String value = null;
        Cookie[] cookies = req.getCookies();
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals("autologin")) {
                value = cookies[i].getValue();
            }
        }
        //2.得到 cookie中的用户名和密码
        if (value != null) {
            String username = value.split("\\.")[0];
            String password = value.split("\\.")[1];

            //3.调用dao获取用户对应的密码
            IUserService service = new UserServiceImpl();
            User user = service.find(username);
            String db_password = user.getUserPwd();
            //4.检查用户带过来的md5的密码和数据库中的密码是否匹配,如匹配则自动登陆
            if (password.equals(WebUtils.md5(db_password))) {
                req.getSession().setAttribute("user", user);

            }
        }
        filterChain.doFilter(req, resp);
    }

    public void destroy() {

    }
}
