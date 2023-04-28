package com.essentls.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Logs the user out
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "LogoutServlet", value = "/logout")
public class LogoutServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Removes the user from the session and invalidates the session. Redirects to
     * the index page.
     *
     * @param req a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        res.sendRedirect(req.getContextPath() + '/');
    }


}




