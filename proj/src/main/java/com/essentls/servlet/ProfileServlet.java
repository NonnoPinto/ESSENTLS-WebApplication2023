package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ProfileServlet", value = "/profile")
public class ProfileServlet extends AbstractDatabaseServlet {
    public final static String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId = Integer.getInteger(request.getParameter("id").trim());
        try {
            User user = new UserProfileInfoDAO(getConnection(),userId).getOutputParam();
            request.setAttribute("user", user);
            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
        }

    }
}
