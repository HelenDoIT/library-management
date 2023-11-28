package com.mylibrary.servlet;

import com.mylibrary.dto.LoginDto;
import com.mylibrary.service.IUserInfoService;
import com.mylibrary.service.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Lai Haimeng
 * @date: 2023/11/27
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private IUserInfoService userInfoService = new UserInfoServiceImpl();

    /**
     * login and sig up
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //todo verify params
        LoginDto loginDto = null;
        if("login".equals(action)){
            loginDto = userInfoService.login(username, password);
            req.setAttribute("user",loginDto);
            switch (loginDto.getCode()){
                case -2:
                    req.setAttribute("passwordError",loginDto.getErrorMsg());
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    break;
                case -1:
                    req.setAttribute("usernameError",loginDto.getErrorMsg());
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    break;
                case 0:
                    if("admin".equals(loginDto.getRole())){
                        req.getRequestDispatcher("/jspviews/book/admin_main.jsp").forward(req, resp);
                    }else{
                        req.getRequestDispatcher("/jspviews/book/user_main.jsp").forward(req, resp);
                    }
                    break;
            }

        }else if("register".equals(action)){
            String role = req.getParameter("role");
            LoginDto register = userInfoService.register(username, password, role);
            req.setAttribute("user",register);
            switch (register.getCode()){
                case -1:
                    req.setAttribute("usernameError",register.getErrorMsg());
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                    break;
                case 0:
                    if("admin".equals(role)){
                        req.getRequestDispatcher("/jspviews/book/admin_main.jsp").forward(req, resp);
                    }else{
                        req.getRequestDispatcher("/jspviews/book/user_main.jsp").forward(req, resp);
                    }
            }
        }

    }

}
