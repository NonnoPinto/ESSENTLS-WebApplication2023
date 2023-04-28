package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.dao.UserCheckEventParticipantDAO;
import com.essentls.dao.UserProfileInfoDAO;
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
import java.sql.Timestamp;

/**
 * Manages the payment of an event or a subscription
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "PaymentServlet", value = "/payment")
public class PaymentServlet extends AbstractDatabaseServlet{

    /**
     * Handles the HTTP {@code GET} method. Redirects to the correct method based on the action parameter.
     *
     * @param request a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


    /**
     * Method that handles the payment of an event.
     *
     * @param request a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     */
    private void eventPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("id") != null && session.getAttribute("sessionUserId") != null){
                Connection transConn = getConnection(); //Connection for transaction
                int eventId = Integer.parseInt(request.getParameter("id"));
                int userId = (int) session.getAttribute("sessionUserId");
                User user = new UserProfileInfoDAO(transConn, userId).access(false).getOutputParam();
                Event event = new EventInfoDAO(transConn, eventId).access(false).getOutputParam();
                Participant p = new Participant(userId, eventId, null, new Timestamp(System.currentTimeMillis()), "{}", user);
                /*
                    If there is place
                 */
                boolean isOrganizer = (session.getAttribute("organizer") != null && (int)session.getAttribute("organizer") == eventId);
                if(new UserCheckEventParticipantDAO(transConn, p, false).access(false).getOutputParam() != null) {
                    if (event.getPrice() > 0 && !isOrganizer) {
                        session.setAttribute("event_"+eventId, "not_payed");
                        request.setAttribute("action", "event");
                        request.setAttribute("event", event);
                        request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
                    } else { //The event is free, I can redirect to the confirmEvent page
                        session.setAttribute("event_"+eventId, "payed");
                        response.sendRedirect(request.getContextPath() + "/confirmEvent?id=" + eventId);
                    }
                }
            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }

    /**
     * Method that handles the payment of a subscription.
     *
     * @param request a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     */
    private void subPayment(HttpServletRequest request, HttpServletResponse response){

        try {
            request.setAttribute("action", "sub");
            request.setAttribute("subPrice", 5.00);
            request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);

        }catch(Exception e){

        }
    }
}
