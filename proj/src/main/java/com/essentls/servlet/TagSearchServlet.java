package com.essentls.servlet;


import com.essentls.dao.*;
import com.essentls.resource.Message;
import com.essentls.resource.Tag;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
