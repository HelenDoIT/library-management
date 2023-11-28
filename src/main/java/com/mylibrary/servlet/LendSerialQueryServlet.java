package com.mylibrary.servlet;

import com.mylibrary.domain.LendSerial;
import com.mylibrary.service.ILendSerialService;
import com.mylibrary.service.impl.LendSerialServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
@WebServlet("/queryLendSerial")
public class LendSerialQueryServlet extends HttpServlet {

    private ILendSerialService lendSerialService = new LendSerialServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        List<LendSerial> lendSerials = lendSerialService.queryByUserId(userId, 0);
        req.setAttribute("lendList",lendSerials);
        req.getRequestDispatcher("/jspviews/lend/mylend.jsp").forward(req, resp);
    }
}
