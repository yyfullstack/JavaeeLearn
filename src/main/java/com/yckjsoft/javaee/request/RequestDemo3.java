package com.yckjsoft.javaee.request;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;

/**
 * Created by yong on 2016/10/8 0008.
 * <p>
 */
public class RequestDemo3 extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("characterEncoding = " + req.getCharacterEncoding());
        req.setCharacterEncoding("utf-8");
        String userid = req.getParameter("userid");
        String username = req.getParameter("username");
        String userpass = req.getParameter("userpass");

        String sex = req.getParameter("sex");
        String dept = req.getParameter("dept");
        String[] insts = req.getParameterValues("inst");
        String note = req.getParameter("note");
        String hiddenField = req.getParameter("hiddenField");

        String instStr = "";
        for (int i = 0; insts != null && i < insts.length; i++) {
            if (i == insts.length - 1) {
                instStr += insts[i];
            } else {
                instStr += insts[i] + ",";
            }
        }

        String htmlStr = "<table>" +
                "<tr><td>填写的编号：</td><td>{0}</td></tr>" +
                "<tr><td>填写的用户名：</td><td>{1}</td></tr>" +
                "<tr><td>填写的密码：</td><td>{2}</td></tr>" +
                "<tr><td>填写的性别：</td><td>{3}</td></tr>" +
                "<tr><td>填写的部门：</td><td>{4}</td></tr>" +
                "<tr><td>填写的兴趣：</td><td>{5}</td></tr>" +
                "<tr><td>填写的说明：</td><td>{6}</td></tr>" +
                "<tr><td>隐藏域的内容：</td><td>{7}</td></tr>" +
                "</table>";
        htmlStr = MessageFormat.format(htmlStr, userid, username, userpass, sex, dept, instStr, note, hiddenField);
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write(htmlStr);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
