package com.essentls.servlet;

import com.essentls.dao.UserLoginDAO;
import com.essentls.resource.Message;
import com.essentls.resource.User;
import com.essentls.utils.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends AbstractDatabaseServlet {

    //public final static String LOGIN_JSP = "/jsp/login.jsp";
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        res.sendRedirect(req.getContextPath() + '/');
    }


}




