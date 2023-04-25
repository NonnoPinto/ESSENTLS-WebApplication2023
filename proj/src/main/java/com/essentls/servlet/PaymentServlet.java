package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.dao.UserCheckEventParticipantDAO;
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
            if (request.getParameter("id") != null && session.getAttribute("sessionUserId") != null){
                Connection transConn = getConnection(); //Connection for transaction
                int eventId = Integer.parseInt(request.getParameter("id"));
                long userId = (long) session.getAttribute("sessionUserId");
                User user = new UserProfileInfoDAO(transConn, userId).access(false).getOutputParam();
                Event event = new EventInfoDAO(transConn, eventId).access(false).getOutputParam();
                Participant p = new Participant(userId, eventId, null, new Timestamp(System.currentTimeMillis()), "{}", user);
                /*
                    If there is place
                 */
                if(new UserCheckEventParticipantDAO(transConn, p, false).access(false).getOutputParam() != null) {
                    if (event.getPrice() > 0) {
                        request.setAttribute("action", "event");
                        request.setAttribute("event", event);
                        request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
                    } else { //The event is free, I can redirect to the confirmEvent page
                        response.sendRedirect(request.getContextPath() + "/confirmEvent?id=" + eventId);
                    }
                }
            }
        }catch(Exception e){
            throw new ServletException(e);
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
