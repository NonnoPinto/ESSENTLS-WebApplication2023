package com.essentls.servlet;

import com.essentls.dao.AdminPaymentListDAO;
import com.essentls.dao.UserPaymentsListDAO;
import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Payment;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PaymentsListServlet", value = "/paymentslist")
public class PaymentsListServlet extends AbstractDatabaseServlet{
    public final static String PAYMENT_SESSION_KEY = "payment";


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //take the request uri
        LogContext.setIPAddress(request.getRemoteAddr());
        LogContext.setResource(request.getRequestURI());
        LogContext.setAction("PAYMENTS-LIST");

        //Getting user
        HttpSession session = request.getSession();
        LOGGER.info("session: %s", session);
        //User user = (User) session.getAttribute("Users");
        //String userEmail = user.getEmail();
        int userId = (int) session.getAttribute("sessionUserId");
        int tier = (int) session.getAttribute("sessionUserTier");
        LOGGER.info("User: %d", userId);
        LOGGER.info("Tier: %d", tier);
        //LOGGER.info("email: %s", userEmail);



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
