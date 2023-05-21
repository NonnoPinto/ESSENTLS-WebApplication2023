package com.essentls.servlet;

import com.essentls.dao.UserProfileInfoDAO;
import com.essentls.resource.Message;
import com.essentls.resource.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Redirects to the unauthorized page
 *
 * @author Mattia Maglie
 * @version 1.00
 * @since 1.00
 */

@WebServlet(name = "ErrorServlet", urlPatterns = {"/error"})
public final class ErrorServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Redirects to the unauthorized page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param resp a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException if an input or output error is detected when the servlet handles the GET request
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        } catch (Exception e) {
            throw e;
        }

    }
    
}
