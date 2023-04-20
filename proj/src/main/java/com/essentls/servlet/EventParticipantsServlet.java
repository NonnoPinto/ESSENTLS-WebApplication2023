package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EventParticipantsServlet", value = "/eventparticipants")
public class EventParticipantsServlet extends AbstractDatabaseServlet {

    public final static String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("Users");
        if(user == null || user.getTier() < 3){
            request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
        }else {
            Integer eventId = Integer.parseInt(request.getParameter("id").trim());
            try {
                Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();
                List<Participant> participants = new AdminParticipantsListDAO(getConnection(), eventId).access().getOutputParam();


                request.setAttribute("event", event);
                request.setAttribute("participants", participants);
                request.getRequestDispatcher("/jsp/eventparticipants.jsp").forward(request, response);
            } catch (SQLException e) {
                LOGGER.error("stacktrace:", e);
                throw new ServletException(e);
            }
        }

    }

}






