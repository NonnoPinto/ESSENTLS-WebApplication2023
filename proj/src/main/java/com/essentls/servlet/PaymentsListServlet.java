package com.essentls.servlet;

import com.essentls.dao.AdminPaymentListDAO;
import com.essentls.dao.UserPaymentsListDAO;
import com.essentls.resource.Payment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Retrieves the list of payments of a user or of all users based on user tier.
 *
 * @author Md Imran Faruck Talukder
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "PaymentsListServlet", value = "/paymentslist")
public class PaymentsListServlet extends AbstractDatabaseServlet{

    /**
     * Handles the HTTP {@code GET} method. Retrieves the list of payments of a user or of all users based on user tier
     * and redirects to the payments list page.
     *
     * @param request a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param response a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setResource(request.getRequestURI());
        LogContext.setAction("PAYMENTS-LIST");

        //Getting user
        HttpSession session = request.getSession();
        LOGGER.info("session: %s", session);
        int userId = (int) session.getAttribute("sessionUserId");
        int tier = (int) session.getAttribute("sessionUserTier");
        LOGGER.info("User: %d", userId);
        LOGGER.info("Tier: %d", tier);


        List<Payment> payment;

        if(tier == 4){

            //Admin Payment List
            try {
                payment = new AdminPaymentListDAO(getConnection()).access().getOutputParam();
                request.setAttribute("Payments", payment);
                LOGGER.info("Admin PaymentsList: %s", payment);
                request.setAttribute("message", "Everything is fine with parameter passing");

                request.getRequestDispatcher("/jsp/paymentslist.jsp").forward(request, response);
            } catch (SQLException e) {
                LOGGER.error("stacktrace:", e);
            }

        }else {

            //Only for User, Not for admin
            try {
                payment = new UserPaymentsListDAO(getConnection(), userId).access().getOutputParam();
                request.setAttribute("Payments", payment);
                LOGGER.info("User PaymentsList: %s", payment);
                request.setAttribute("message", "Everything is fine with parameter passing");
                request.getRequestDispatcher("/jsp/paymentslist.jsp").forward(request, response);

            } catch (SQLException e) {
                LOGGER.error("stacktrace:", e);
            }

        }


    }

}
