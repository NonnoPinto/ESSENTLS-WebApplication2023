package com.essentls.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "TagSearchServlet", urlPatterns = {"", "/tag-search"})
@MultipartConfig
public final class TagSearchServlet extends AbstractDatabaseServlet {

    /**
     * @param req //request
     * @param res //response
     * @throws ServletException //exception servlet
     * @throws IOException      //exceptionIO
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setResource(req.getRequestURI());
        LogContext.setResource(req.getRequestURI());
        LogContext.setAction("SEARCH TAG");

        req.getRequestDispatcher("/jsp/tag-search.jsp").forward(req, res);
    }
}
