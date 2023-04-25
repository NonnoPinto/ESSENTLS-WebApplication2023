package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.Event;
import com.essentls.resource.Participant;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(name = "ConfirmEventServlet", value = "/confirmEvent")
public class ConfirmEventServlet extends AbstractDatabaseServlet{


    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Connection transConn = null;
        try {
            transConn = getConnection();
            transConn.setAutoCommit(false);
            if (request.getParameter("id") == null || session.getAttribute("sessionUserId") == null) {
                transConn.rollback();
                transConn.close();
                request.getRequestDispatcher("/jsp/unauthorized.jsp").forward(request, response);
            } else {
                int eventId = Integer.parseInt(request.getParameter("id"));
                long userId = (long) session.getAttribute("sessionUserId");
                if(!this.startPartecipation(transConn, eventId, userId)){
                    transConn.rollback();
                    transConn.close();
                }else {
                    transConn.commit(); //Partecipation confirmed, event payed
                    transConn.close();
                    Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();
                    String[] attributes = event.getAttributes();
                    request.setAttribute("event", event);
                    request.setAttribute("attributes", attributes);
                    request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
                }
            }
        }catch(SQLException e){
            try {
                transConn.rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            throw new ServletException(e);
        }
    }

    protected synchronized boolean startPartecipation(Connection transConn, int eventId, long userId) throws ServletException {
        try {
            Event event = new EventInfoDAO(transConn, eventId).access(false).getOutputParam();
            User user = new UserProfileInfoDAO(transConn, userId).access(false).getOutputParam();
            if (user == null || user.getTier() < event.getVisibility()) { //Auth check
                return false;
            } else {
                try {
                    Participant p = new Participant(user.getId(), eventId, null, new Timestamp(System.currentTimeMillis()), "{}", user);
                    p = new UserCheckEventParticipantDAO(transConn, p, (event.getPrice() > 0)).access(false).getOutputParam();
                    if (p != null) {
                        if ((new UserJoinsEventDAO(transConn, p)).access(false).getOutputParam()) {
                            return true;
                        } else {
                            throw new ServletException("Event was maybe full");
                        }
                    } else {
                        throw new ServletException("Trans error");
                    }
                } catch (SQLException e) {
                    LOGGER.error(e);
                    throw new ServletException(e);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }
}
