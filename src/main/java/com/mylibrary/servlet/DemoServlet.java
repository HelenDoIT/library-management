package com.mylibrary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/26
 */
@WebServlet("/demo")
public class DemoServlet extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        System.out.println("before demo");
        String name = req.getParameter("name");
        out.println("s1"+name);
        //RequestDispatcher
        req.getRequestDispatcher("/htmlviews/demo.html").forward(req, resp);
        System.out.println("after demo");
    }
}
