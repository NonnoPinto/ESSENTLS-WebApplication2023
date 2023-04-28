package com.essentls.servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Directs to the cause search page.
 *
 * @author Matteo Villani
 * @version 1.00
 * @since 1.00
 */

@WebServlet(name = "CauseSearchServlet", urlPatterns = {"", "/cause-search"})
public final class CauseSearchServlet extends AbstractDatabaseServlet {

    /**
     * Handles the HTTP {@code GET} method. Redirects to the cause search page.
     *
     * @param req a {@link HttpServletRequest} object that contains the request the client has made of the servlet
     * @param res a {@link HttpServletResponse} object that contains the response the servlet sends to the client
     *
     * @throws ServletException if the request for the GET could not be handled
     * @throws IOException      if an input or output error is detected when the servlet handles the GET request
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("SEARCH CAUSE");

        req.getRequestDispatcher("/jsp/cause-search.jsp").forward(req, res);
    }
}
