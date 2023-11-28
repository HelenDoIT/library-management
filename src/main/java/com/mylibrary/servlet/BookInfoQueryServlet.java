package com.mylibrary.servlet;

import com.mylibrary.dao.IBookInfoDao;
import com.mylibrary.domain.BookInfo;
import com.mylibrary.service.IBookInfoService;
import com.mylibrary.service.impl.BookInfoServiceImpl;

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
@WebServlet("/queryBook")
public class BookInfoQueryServlet extends HttpServlet {

    private IBookInfoService bookInfoService = new BookInfoServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String bookname = req.getParameter("bookname");
        BookInfo bookInfo = bookInfoService.queryByName(bookname);
        req.setAttribute("list",bookInfo);
        if("admin".equals(role)){
            req.getRequestDispatcher("/jspviews/book/admin_books.jsp").forward(req, resp);
            return;
        }
        req.getRequestDispatcher("/jspviews/book/user_books.jsp").forward(req, resp);
    }
}
