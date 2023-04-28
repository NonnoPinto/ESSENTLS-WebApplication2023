package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.Event;
import com.essentls.resource.Payment;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Inserts a payment in the database.
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */

@WebServlet(name = "AddPaymentServlet", value = "/add_payment")
public class AddPaymentServlet extends AbstractDatabaseServlet{

    /**
     * Handles the HTTP {@code GET} method. Checks if the payment is for an event or a subscription and redirects to the
     * correct method.
     *
     * @param request  a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */

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

    /**
     * Retrieves the payment information from the request and inserts it in the database. Redirects to the event
     * confirmation page.
     *
     * @param request  a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the POST could not be handled
     */

    private void eventPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("id") != null && session.getAttribute("sessionUserId") != null){

                int eventId = Integer.parseInt(request.getParameter("id"));
                int userId = (int) session.getAttribute("sessionUserId");
                User user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
                Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();

                if(user != null && event != null && session.getAttribute("event_"+eventId).equals("not_payed")) {

                    if (new UserPaymentSubmitDAO(getConnection(), new Payment(0, userId, eventId, "Card", event.getPrice(), new Date(System.currentTimeMillis()), "Event")).access().getOutputParam()) {
                        session.setAttribute("event_"+eventId, "payed");
                        response.sendRedirect(request.getContextPath() + "/confirmEvent?id=" + eventId);
                    }else{
                        throw new SQLException("Error during payment insert");
                    }
                }else{
                    if(user != null && event != null)
                        response.sendRedirect(request.getContextPath() + "/confirmEvent?id=" + eventId);
                    else
                        throw new ServletException("Unexpected error");
                }
            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }

    /**
     * Retrieves the payment information from the request and inserts it in the database. Redirects to the home page.
     *
     * @param request  a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the POST could not be handled
     */

    private void subPayment(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("sessionUserId") != null) {
                int userId = (int) session.getAttribute("sessionUserId");
                if (new UserPaymentSubmitDAO(getConnection(), new Payment(0, userId, 0, "Card", 5, new Date(System.currentTimeMillis()), "Membership")).access().getOutputParam()) {
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }
}
