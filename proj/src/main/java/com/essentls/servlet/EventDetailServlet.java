package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "EventDetailServlet", value = "/eventdetail")
public class EventDetailServlet extends AbstractDatabaseServlet {

    public final static String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer eventId = Integer.getInteger(request.getParameter("id").trim());
        try {
            Event event = new EventInfoDAO(getConnection(),eventId).getOutputParam();
            request.setAttribute("event", event);
            request.getRequestDispatcher("/jsp/eventdetail.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
        }

    }

}






