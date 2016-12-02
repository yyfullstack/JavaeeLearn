package com.login.web.controller;

import com.login.domain.User;
import com.login.exception.UserExistException;
import com.login.service.IUserService;
import com.login.service.impl.UserServiceImpl;
import com.login.util.WebUtils;
import com.login.web.formbean.RegisterFormBean;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by yong on 2016/10/31 0031.
 * 处理用户注册的servlet
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegisterFormBean formBean = WebUtils.request2Bean(req, RegisterFormBean.class);
        if (formBean.validate() == false) {
            req.setAttribute("formbean", formBean);
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            return;
        }
        User user = new User();
        ConvertUtils.register(new DateLocaleConverter(), Date.class);
        try {
            //把表单的数据填充到javabean中
            BeanUtils.copyProperties(user, formBean);
            user.setId(WebUtils.makeId());
            IUserService service = new UserServiceImpl();
            service.registerUser(user);
            String message = String.format("注册成功!! 3秒后为您自动跳到登录页面！！" +
                    "<meta http-equiv='refresh' content='3;url=%s'/>", req.getContextPath() + "/servlet/LoginUIServlet");
            req.setAttribute("message", message);
            req.getRequestDispatcher("/message.jsp").forward(req, resp);

        } catch (UserExistException e) {
            formBean.getErrors().put("userName", "注册用户已存在！！");
            req.setAttribute("formbean", formBean);
            req.getRequestDispatcher("/pages/register.jsp").forward(req, resp);
            //e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("message", "对不起，注册失败！！");
            req.getRequestDispatcher("/pages/message.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
