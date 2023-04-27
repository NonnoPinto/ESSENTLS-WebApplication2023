package com.essentls.servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "CauseSearchServlet", urlPatterns = {"", "/cause-search"})
@MultipartConfig
public final class CauseSearchServlet extends AbstractDatabaseServlet {

    /**
     * Creates a new cause into the database.
     *
     * @param req the HTTP request from the client.
     * @param res the HTTP response from the server.
     *
     * @throws IOException if any error occurs in the client/server communication.
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("SEARCH CAUSE");

        req.getRequestDispatcher("/jsp/cause-search.jsp").forward(req, res);
    }
}
