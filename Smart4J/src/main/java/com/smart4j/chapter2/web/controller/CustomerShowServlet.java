package com.smart4j.chapter2.web.controller;


import com.smart4j.chapter2.domain.Customer;
import com.smart4j.chapter2.service.ICustomerService;
import com.smart4j.chapter2.service.impl.CustomerServiceImpl;
import com.smart4j.chapter2.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yong on 2016/10/31 0031.
 * 处理用户登录的servlet
 */
@WebServlet("/customer_show")
public class CustomerShowServlet extends HttpServlet {
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
            req.getRequestDispatcher("/view/customer_show.jsp").forward(req, resp);
        }
    }
}
