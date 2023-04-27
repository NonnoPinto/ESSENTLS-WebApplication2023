package com.essentls.servlet;

import com.essentls.dao.*;
import com.essentls.resource.*;

import java.io.*;
import java.net.URL;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet(name = "CauseCreateServlet", urlPatterns = {"", "/cause-create"})
@MultipartConfig
public final class CauseCreateServlet extends AbstractDatabaseServlet {

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
        LogContext.setAction("CREATE CAUSE");

        req.getRequestDispatcher("/jsp/cause-create.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        LogContext.setIPAddress(req.getRemoteAddr());
        LogContext.setAction("CREATE CAUSE");

        //request parameters
        String name = null;

        try{
            //retrieves the params
            id = req.getParameter("causeName");

            LogContext.setResource(req.getParameter("name"));

            URL url = CauseCreateServlet.class.getProtectionDomain().getCodeSource().getLocation();
            
            //creates a new cause obj from the retrived params
            c = new Cause(name);

            // creates a new object for accessing the database and stores the event
            new CausesCreationDAO(getConnection(), c).access();

            m = new Message(String.format("Cause \""+c.getName()+"\" successfully created."));

            LOGGER.info("Cause \""+c.getName()+"\" successfully created in the database.");

            req.setAttribute("message", m);
            req.getRequestDispatcher("/jsp/cause-create.jsp").forward(req, res);

        } catch (SQLException ex) {
            
            m = new Message("Cannot create the cause: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

            LOGGER.error("Cannot create the cause: unexpected error while accessing the database.", ex);
        }
    }

}
