package com.mylibrary.servlet;


import com.mylibrary.domain.BookInfo;
import com.mylibrary.dto.BookInfoDto;
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
 * @date: 2023/11/27
 */
@WebServlet("/bookInfo")
public class BookInfoServlet extends HttpServlet {

    private IBookInfoService bookInfoService = new BookInfoServiceImpl();

    /**
     * add or delete book
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String name = req.getParameter("bookname");
        String author = req.getParameter("author");
        Integer inventory = Integer.valueOf(req.getParameter("inventory"));
        String role = req.getParameter("role");
        switch (action){
            case "add":
                BookInfo bookInfo = new BookInfo(null,name,author,inventory);
                int add = bookInfoService.add(bookInfo);
                List<BookInfo> bookInfoList = bookInfoService.listAll();
                req.setAttribute("list",bookInfoList);
                req.getRequestDispatcher("/jspviews/book/admin_books.jsp").forward(req, resp);
                break;
            case "delete":
                Long bookId = Long.valueOf(req.getParameter("bookId"));
                BookInfoDto infoDto = bookInfoService.delete(bookId);
                if (infoDto.getCode() == -1) {
                    req.setAttribute("errorMsg", infoDto.getErrorMsg());
                    req.getRequestDispatcher("/jspviews/book/admin_books.jsp").forward(req, resp);
                }else {
                    req.getRequestDispatcher("/jspviews/book/admin_books.jsp").forward(req, resp);
                }
        }
    }

}
