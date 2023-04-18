package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet(name = "EventDetailServlet", value = "/eventdetail")
public class EventDetailServlet extends AbstractDatabaseServlet {

    public final static String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // write the HTML page
        /*out.printf("test: " + Integer.getInteger(request.getParameter("id").trim()));
        out.flush();
        out.close();*/
        Integer eventId = Integer.parseInt(request.getParameter("id").trim());
        try {
            Event e = new EventInfoDAO(getConnection(),eventId).access().getOutputParam();
            request.setAttribute("event", e);
            request.getRequestDispatcher("/jsp/eventdetail.jsp").forward(request, response);
        } catch (SQLException e) {
            LOGGER.error("stacktrace:", e);
            throw new ServletException(e);
        }

    }

}






