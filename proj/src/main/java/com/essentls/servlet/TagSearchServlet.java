package com.essentls.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Redirects to the tag search page
 *
 * @author Matteo Villani
 * @version 1.00
 * @since 1.00
 */
@WebServlet(name = "TagSearchServlet", urlPatterns = {"", "/tag-search"})
@MultipartConfig
public final class TagSearchServlet extends AbstractDatabaseServlet {

    /**
     * Redirects to the tag search page
     * @param req a {@code HttpServletRequest} object that contains the request the client has made of the servlet.
     * @param res a {@code HttpServletResponse} object that contains the response the servlet sends to the client.
     * @throws ServletException if the request for the GET could not be handled.
     * @throws IOException if an input or output error is detected when the servlet handles the GET request.
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("SEARCH TAG");

        req.getRequestDispatcher("/jsp/tag-search.jsp").forward(req, res);
    }
}
