package com.essentls.servlet;

import com.essentls.dao.EventInfoDAO;
import com.essentls.resource.Event;
import com.essentls.resource.Payment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "PaymentServlet", value = "/payment")
public class PaymentServlet extends AbstractDatabaseServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Payment payment = (Payment) session.getAttribute("Payments");
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

    private void eventPayment(HttpServletRequest request, HttpServletResponse response){
        try {
            if (request.getParameter("id") != null) {
                int eventId = Integer.parseInt(request.getParameter("id"));
                Event event = new EventInfoDAO(getConnection(), eventId).access().getOutputParam();
                float eventPrice = event.getPrice();
                if(eventPrice > 0) {
                    request.setAttribute("action", "event");
                    request.setAttribute("event", event);
                    request.getRequestDispatcher("/jsp/payment.jsp").forward(request, response);
                }else{ //The event is free, I can redirect to the joinEvent page
                    response.sendRedirect(request.getContextPath() + "/joinEvent?id=" + eventId);
                }
            }
        }catch(Exception e){

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
