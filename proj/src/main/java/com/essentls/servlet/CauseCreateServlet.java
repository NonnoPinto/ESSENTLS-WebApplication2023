package com.essentls.servlet;


import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Directs to the cause creation page.
 *
 * @author Matteo Villani
 * @version 1.00
 * @since 1.00
 */

@WebServlet(name = "CauseCreateServlet", urlPatterns = {"", "/cause-create"})
public final class CauseCreateServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Redirects to the cause creation page.
     *
     * @param req a {@code HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@code HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
    */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("CREATE CAUSE");

        req.getRequestDispatcher("/jsp/cause-create.jsp").forward(req, res);
    }
}
