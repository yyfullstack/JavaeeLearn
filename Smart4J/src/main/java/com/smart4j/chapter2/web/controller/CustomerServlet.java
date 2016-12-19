package com.smart4j.chapter2.web.controller;


import com.smart4j.chapter2.domain.Customer;
import com.smart4j.chapter2.service.ICustomerService;
import com.smart4j.chapter2.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yong on 2016/10/31 0031.
 * 处理用户登录的servlet
 */
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

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
        List<Customer> list = service.getCustomerList();
        req.setAttribute("customerList", list);
        req.getRequestDispatcher("/view/customer.jsp").forward(req, resp);
    }
}
