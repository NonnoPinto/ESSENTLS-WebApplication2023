package com.essentls.servlet;

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
    }

}
