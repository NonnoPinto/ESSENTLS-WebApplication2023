package com.essentls.servlet;

import com.essentls.dao.AdminPaymentListDAO;
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

@WebServlet(name = "PaymentsListServlet", value = "/paymentslist/")
public class PaymentsListServlet extends AbstractDatabaseServlet{
    public final static String USER_SESSION_KEY = "payment";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Payment payment = (Payment) session.getAttribute("Payments");
//        long userId = user.getId();
        LOGGER.info("Payments: %s", payment);



//        try {
//            payment = new AdminPaymentListDAO(getConnection());
//            request.setAttribute("Users", user);
//            request.getRequestDispatcher("/jsp/profile.jsp").forward(request, response);
//        } catch (SQLException e) {
//            LOGGER.error("stacktrace:", e);
//        }

    }

}
