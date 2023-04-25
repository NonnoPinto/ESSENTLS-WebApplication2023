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
import java.sql.SQLException;
import java.sql.Timestamp;

@WebServlet(name = "PaymentServlet", value = "/payment")
public class PaymentServlet extends AbstractDatabaseServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        switch (action){
            case "event":
                eventPayment(request, response);
                break;
            case "sub":
                subPayment(request, response);
                break;
        }
    }

    private void eventPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("id") != null && session.getAttribute("userId") != null){
                Connection transConn = getConnection(); //Connection for transaction
                int eventId = Integer.parseInt(request.getParameter("id"));
                long userId = (long) session.getAttribute("userId");
                Event event = new EventInfoDAO(transConn, eventId).access(false).getOutputParam();
                if(this.startPartecipation(transConn, eventId, userId)) {
                    session.setAttribute("transConn", transConn);
                    if (event.getPrice() > 0) {
                        request.setAttribute("action", "event");
                        request.setAttribute("event", event);
                        request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
                    } else { //The event is free, I can redirect to the joinEvent page
                        response.sendRedirect(request.getContextPath() + "/joinEvent?id=" + eventId);
                    }
                }
            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }

    protected boolean startPartecipation(Connection transConn, int eventId, long userId) throws ServletException {
        try {
            Event event = new EventInfoDAO(transConn, eventId).access(false).getOutputParam();
            User user = new UserProfileInfoDAO(transConn, userId).access(false).getOutputParam();
            if (user == null || user.getTier() < event.getVisibility()) { //Auth check
                return false;
            } else {
                try {
                    Participant p = new Participant(user.getId(), eventId, null, new Timestamp(System.currentTimeMillis()), "{}", user);
                    if ((new UserJoinsEventDAO(transConn, p)).access(false).getOutputParam()) {
                        return true;
                    } else {
                        throw new ServletException("Event was maybe full");
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

    private void subPayment(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setAttribute("action", "sub");
            request.setAttribute("subPrice", 5.00);
            request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);

        }catch(Exception e){

        }
    }
}
