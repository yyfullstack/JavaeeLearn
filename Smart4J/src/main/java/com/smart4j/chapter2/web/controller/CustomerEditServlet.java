package com.smart4j.chapter2.web.controller;


import com.smart4j.chapter2.domain.Customer;
import com.smart4j.chapter2.service.ICustomerService;
import com.smart4j.chapter2.service.impl.CustomerServiceImpl;
import com.smart4j.chapter2.util.StringUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yong on 2016/10/31 0031.
 * 处理用户登录的servlet
 */
@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet {
    private ICustomerService service;

    @Override
    public void init() throws ServletException {
        service = new CustomerServiceImpl();
    }

    /**
     * 进入创建客户 界面
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (StringUtil.isNotEmpty(id)) {
            Customer customer = service.getCustomer(Integer.parseInt(id));
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/view/customer_edit.jsp").forward(req, resp);
        }
    }

    /**
     * 处理创建客户 请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        String strId = req.getParameter("id");
        int id = Integer.parseInt(strId);
        fieldMap.put("name", req.getParameter("name"));
        fieldMap.put("contact", req.getParameter("contact"));
        fieldMap.put("telephone", req.getParameter("telephone"));
        fieldMap.put("email", req.getParameter("email"));
        if (service.updateCustomer(id, fieldMap)) {
            resp.sendRedirect(req.getContextPath() + "/customer");
        }
    }
}
