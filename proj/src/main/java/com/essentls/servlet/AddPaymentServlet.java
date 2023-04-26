package com.essentls.servlet;

import com.essentls.dao.*;
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
import java.sql.Timestamp;

@WebServlet(name = "AddPaymentServlet", value = "/add_payment")
public class AddPaymentServlet extends AbstractDatabaseServlet{

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

                int eventId = Integer.parseInt(request.getParameter("id"));
                long userId = (long) session.getAttribute("sessionUserId");
                User user = new UserProfileInfoDAO(getConnection(), userId).access().getOutputParam();
                Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();

                if(user != null && event != null && session.getAttribute("event_"+eventId).equals("not_payed")) {

                    if (new UserPaymentSubmitDAO(getConnection(), new Payment(0, userId, eventId, "Card", event.getPrice(), new Date(System.currentTimeMillis()), "")).access().getOutputParam()) {
                        session.setAttribute("event_"+eventId, "payed");
                        response.sendRedirect(request.getContextPath() + "/confirmEvent?id=" + eventId);
                    }else{
                        throw new SQLException("Error during payment insert");
                    }
                }
            }
        }catch(Exception e){
            throw new ServletException(e);
        }
    }

    private void subPayment(HttpServletRequest request, HttpServletResponse response){
        //TODO Payment of subscription
    }
}
