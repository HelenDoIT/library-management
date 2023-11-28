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

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/28
 */
@WebServlet("/lend")
public class LendSerialServlet extends HttpServlet {

    private ILendSerialService lendSerialService = new LendSerialServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Long userId = Long.valueOf(req.getParameter("userId"));
        Long bookId = Long.valueOf(req.getParameter("bookId"));
        switch (action){
            case "borrow":
                LendSerial lendSerial = new LendSerial(null,userId,bookId,1,0,null,null);
                int i = lendSerialService.recodeLending(lendSerial);
                req.getRequestDispatcher("/jspviews/lend/mylend.jsp").forward(req, resp);
                break;
            case "return":
                Long serialNo = Long.valueOf(req.getParameter("lendSerialNo"));
                lendSerialService.returnBook(serialNo);
                req.getRequestDispatcher("/jspviews/lend/mylend.jsp").forward(req, resp);
                break;
        }

    }
}
