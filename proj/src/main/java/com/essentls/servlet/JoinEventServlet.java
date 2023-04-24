package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.dao.UserJoinsEventDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.Participant;
import com.essentls.resource.Payment;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet(name = "JoinEventServlet", value = "/joinEvent")
public class JoinEventServlet extends AbstractDatabaseServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("id") == null || session.getAttribute("userId") == null){
                request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
            }else {
                long eventId = Long.parseLong(request.getParameter("id"));
                Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();
                long userId = (long) session.getAttribute("userId");
                User user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
                if (user == null || user.getTier() < event.getVisibility()) { //Auth check
                    request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
                } else {
                    Participant p = new Participant(user.getId(), eventId, "WaitingList", new Date(System.currentTimeMillis()), "{}");
                    try {
                        if ((new UserJoinsEventDAO(getConnection(), p)).access().getOutputParam()) {
                            response.sendRedirect(request.getContextPath() + "/eventdetail?id=" + eventId);
                        } else {
                            throw new ServletException("Error during event participation");
                        }
                    } catch (SQLException e) {
                        LOGGER.error(e);
                        throw new ServletException(e);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
